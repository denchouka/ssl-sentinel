package cool.tch.util;

import cool.tch.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

import static cool.tch.common.Constant.TASK_MAIL_FROM_PERSONAL;

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
        javaMailSender.setDefaultEncoding("UTF-8");
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
     * @param content 邮件内容
     */
    @Async("threadPoolTaskExecutor")
    public void send(String to, String subject, String content) {
        try {
            // 创建一个MINE消息
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            // 主题
            helper.setSubject(subject);
            // From
            helper.setFrom(Objects.requireNonNull(javaMailSender.getUsername()), TASK_MAIL_FROM_PERSONAL);
            // To
            helper.setTo(to);
            // 发送日期
            helper.setSentDate(new Date());
            // 正文
            helper.setText(content);
            // 发送
            javaMailSender.send(mimeMessage);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
