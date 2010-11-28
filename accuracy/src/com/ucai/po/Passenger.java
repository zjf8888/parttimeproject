package com.ucai.po;

/**
 * 乘客
 * @author lixu
 *
 */
public class Passenger {
    //乘客姓名   一定是简体中文，不包含生僻字 英文名 的格式 first name/ last name
    public String pasName;
    //乘客类型 1/成人、2/儿童、3/婴儿
    public String pasType;
    //如果乘客类型3/婴儿时填写 出生日期
    public String pasYE;
    //证件类型 1、身份证，2、港澳通行证，3、护照，4、军官证，5、台胞证，6、国际海员证，7、回乡证，8、其他
    public String pasBirthday;
    ///证件号码
    public String pasBirthNo;
    //保险数量
    public String insurance_num;
}
