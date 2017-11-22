package pers.cc.design.command.service;

/**
 * Created by cc on 2017/7/29.
 */
public interface Command {

    /**
     * 执行命令接口
     */
    void execute();

    /**
     * 撤销命令接口
     */
    void undo();


}
