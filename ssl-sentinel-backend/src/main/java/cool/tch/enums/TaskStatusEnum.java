package cool.tch.enums;

import cool.tch.exception.BusinessException;

/**
 * @author denchouka
 * @description 任务状态枚举类
 * @date 2024/12/17 21:21
 */
public enum TaskStatusEnum {

    /**
     * 未执行
     */
    NOT_STARTED(0, "未执行"),

    /**
     * 执行中
     */
    IN_PROGRESS(1, "执行中"),

    /**
     * 执行完成
     */
    COMPLETED(2, "执行完成");

    private final int status;

    private final String value;

    TaskStatusEnum(int status, String value) {
        this.status = status;
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }

    public static TaskStatusEnum getByCode(int status) {
        for (TaskStatusEnum taskStatusEnum : TaskStatusEnum.values()) {
            if (taskStatusEnum.getStatus() == status) {
                return taskStatusEnum;
            }
        }
        throw new BusinessException("任务状态:status " + status + " 不正确！");
    }

}
