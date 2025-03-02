package cool.tch.util;

import cool.tch.entity.Task;
import cool.tch.exception.BusinessException;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

import static cool.tch.common.Constant.*;

/**
 * @author denchouka
 * @description 发送邮件工具类
 * @date 2025/1/9 21:53
 */
@Component
public class MailUtils {

    private JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

    @Value("${mail.host}")
    private String emailHost;
    @Value("${mail.username}")
    private String emailUsername;
    @Value("${mail.password}")
    private String emailPassword;
    @Value("${mail.port}")
    private Integer emailPort;

    @PostConstruct
    public void init() {
        javaMailSender.setHost(emailHost);
        javaMailSender.setUsername(emailUsername);
        javaMailSender.setPassword(emailPassword);
        javaMailSender.setPort(emailPort);
        javaMailSender.setDefaultEncoding(ENCODING_DEFAULT);
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.debug", "true");
        p.setProperty("mail.smtp.ssl.enable", "true");
        javaMailSender.setJavaMailProperties(p);
    }

    /**
     * 发送邮件
     * @param to 邮件接收者
     * @param subject 邮件主题
     * @param task 任务数据
     */
    @Async("threadPoolTaskExecutor")
    public void send(String to, String subject, Task task) {
        try {
            // 创建一个MINE消息
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, ENCODING_DEFAULT);
            // 主题
            helper.setSubject(subject);
            // From
            helper.setFrom(Objects.requireNonNull(javaMailSender.getUsername()), TASK_MAIL_FROM_PERSONAL);
            // To
            helper.setTo(to);
            // 发送日期，指定时区为UTC
            ZonedDateTime now = ZonedDateTime.now(ZoneId.of(ZONEID_UTC));
            Date from = Date.from(now.toInstant());
            helper.setSentDate(from);
            // 正文
            helper.setText(messageContent(task));
            // 发送
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private static String messageContent(Task task) {
        return  "用户你好，域名【" + task.getDomainName() + "】的SSL证书即将过期，请尽快处理，详细信息如下。\n\n" +
                "【域名】" + task.getDomainName() + "\n" +
                "【申请平台】" + task.getApplicationPlatform() + "\n" +
                "【使用平台】" + task.getUsagePlatform() + "\n" +
                "【用途】" + task.getPurpose() + "\n" +
                "【过期日期】" + DateUtils.parseDate(task.getDdl()) + "\n" +
                "【提醒内容】" + task.getContent() + "\n" +
                "【备注】" + task.getRemark() + "\n\n" +
                "Copyright © 2024 - " + DateUtils.thisYear() + " SSL Sentinel. \nAll Rights Reserved.\n" +
                "Powered by https://ssl.tch.cool/";
    }
}
