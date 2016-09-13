package com.ikamobile.pa.common.utils;

import com.ikamobile.pa.common.enums.EntityTypeEnum;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.google.gson.Gson;

public class StringUtils {

    public static boolean isNullOrEmpty(String str)
    {
        return str==null||"".equals(str);
    }
    
    public static boolean isNotBlank(String str){
        return org.apache.commons.lang3.StringUtils.isNotBlank(str);
    }

     
     public static boolean isEqual(String str1,String str2)
     {
         if(str1==null||str2==null)
         {
             return false;
         }
         return str1.equals(str2);
     }
     
     public static String md5(String data) throws Exception
     {
         MessageDigest md = MessageDigest.getInstance("MD5");
         md.update(data.getBytes());
         byte[] b = md.digest();
         StringBuffer sb = new StringBuffer();
         for (int i = 0; i < b.length; i++) {
             String hex = Integer.toHexString(b[i] & 0xFF);
             hex = (hex.length() == 1 ? "0" : "") + hex;
             sb.append(hex);
         }
         return sb.toString();
     }

    public static String format(Date date)
    {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String format(Date date,String pattern)
    {
        if (date == null || pattern == null || "".equals(pattern.trim()))
           return null;
        String str = null;
        try {
            SimpleDateFormat parser = new SimpleDateFormat(pattern);
            str = parser.format(date);
        } catch (Exception e) {
            return null;
        }
        return str;
    }

    /**
     * 获得随机字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length)
    {
        String baseStr="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder str=new StringBuilder();
        Random random=new Random();
        int baseLength=baseStr.length();
        for(int i=0;i<length;i++)
        {
            str.append(baseStr.charAt(random.nextInt(baseLength-1)));
        }
        return str.toString();
    }


    /**
     * 分割姓名
     * 输入："ao ba ma"       ""             "aobama"
     * 输出：["Ao","Bama"]    ["",""]        ["Aobama",""]
     * @param name
     * @return
     */
    public static String[] splitName(String name) {
        String[] ret = new String[]{"",""};
        if(isNullOrEmpty(name)){
            return ret;
        }
        String nameStr = name.trim();
        int w = nameStr.indexOf(" ");
        if(w>0){  //
            String fn = nameStr.substring(0, w);
            String ln = nameStr.substring(w).replaceAll(" ", "");
            ret[0]=upperFirstLetter(fn);
            ret[1]=upperFirstLetter(ln);
        }else{
            ret[0]=upperFirstLetter(nameStr);
        }
        return ret;
    }

    /**
     * 字符串首字母大写
     * @param string
     * @return
     */
    public static String upperFirstLetter(String string){
        if(isNullOrEmpty(string)){
            return string;
        }
        StringBuilder sb = new StringBuilder(string);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return  sb.toString();
    }
    
    /**
     * 是否是 汉字
     */
    public static boolean isChinese(char c) {
        boolean result = false;
        if (c >= 19968 && c <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
            result = true;
        }
        return result;
    }
    
    /**
     * 是否是三字码
     */
    public static boolean isValidateIata(String iata) {
        boolean result = true;
        if(iata.length() != 3){
            return false;
        }

        if(!iata.matches("^[a-zA-Z]+$")){
            return false;
        }
        return result;
    }

    /**
     * 获取UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 根据证件获取生日和性别
     * @param certCode
     * @return
     */
    public static String[] getBirthdayAndGender(String certCode) {
        try {
            if (certCode == null) return null;
            String birthday = null, gender = null;
            if (certCode.length() == 15) {
                birthday = "19" + certCode.substring(6, 12);
                String flag = certCode.substring(14, 15);
                if (Integer.valueOf(flag) % 2 == 0) gender = "FEMALE";
                else gender = "MALE";
                Date d = DateTimeUtils.convertStringToDate(birthday, "yyyyMMdd");
                birthday = DateTimeUtils.convertDateToString(d, "yyyy-MM-dd");
                return new String[]{birthday, gender};

            } else if (certCode.length() == 18) {
                birthday = certCode.substring(6, 14);
                String flag = certCode.substring(16, 17);
                if (Integer.valueOf(flag) % 2 == 0) gender = "FEMALE";
                else gender = "MALE";

                Date d = DateTimeUtils.convertStringToDate(birthday, "yyyyMMdd");
                birthday = DateTimeUtils.convertDateToString(d, "yyyy-MM-dd");
                return new String[]{birthday, gender};
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }



    /**
     * 生成6位数字随机数（验证码）
     *
     * @return
     */
    public static String createRandom(int length) {
        String strRandom = "";
        for (int i = 0; i < length; i++) {
            strRandom += String.valueOf(new Random().nextInt(10));
        }
        return strRandom;
    }

    /**
     * 手机号码屏蔽关键数字,屏蔽4-10位
     * @author wenyueming
     * @time 2015-7-22 下午7:38:05
     * @description description
     */
    public static String encryptPhoneNum(String phoneNum){
        StringBuffer s = new StringBuffer();
        for (int i=0;i<phoneNum.length();i++) {
            if (i>2&&i<9) {
                s.append("*");
                continue;
            }
            s.append(phoneNum.toCharArray()[i]);
        }
        return  s.toString();
    }


    public static String genCode(EntityTypeEnum typeEnum){
        StringBuffer code = new StringBuffer(typeEnum.getPrefix());
        switch (typeEnum){
            case TASK:
                code.append(DateTimeUtils.convertTimeToString(new Date(),"yyyyMMdd")).append(getRandomString(4).toUpperCase());
                break;
            case ORDER:
                code.append(DateTimeUtils.convertTimeToString(new Date(),"yyyyMMdd")).append(getRandomString(4).toUpperCase());
                break;
            case DRIVER:
                break;
            case VEHICLE:
                break;
        }

        return code.toString();
    }

    public static boolean isMobile(String mobile){
        boolean flag = false;
        try{
            Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Matcher matcher = regex.matcher(mobile);
            flag = matcher.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;

    }

    /**
     * 获取某个字符串最后一位数字字符串
     * 若不存在则返回 -1
     * @param target
     * @return
     */
    public static int getLastNumber(String target){
        if(isNullOrEmpty(target)){
            return -1;
        }
        for (int i = target.length()-1; i >= 0; i--) {
            if(target.charAt(i) >='0'&&target.charAt(i) <='9'){
                return Integer.parseInt(Character.toString(target.charAt(i)));
            }
        }
        return -1;
    }


    /**
     * 反转字符串
     * 如果为null，或者空字符串则返回target
     * @param target
     * @return
     */
    public static String reverse(String target){
        if(isNullOrEmpty(target)||target.length() == 1){
            return target;
        }
        char[] chars = target.toCharArray();
        for(int i = 0, mid = chars.length >> 1, j = target.length() -1
                ;i < mid;i++){
            char temp = chars[i];
            chars[i] = chars[j-i];
            chars[j-i] = temp;
        }
        return new String(chars);

    }




    /**
     * 判断一个车牌号在成都某个日期是否限行
     * 不考虑政策生效时间范围，只考虑现行政策规则：
     *  |星期|限行尾号|
     *  |---|---|---|
     *  |周一|1，6|
     *  |周二|2，7|
     *  |周三|3，8|
     *  |周四|4，9|
     *  |周五|5，0|
     *  |周六|任意行|
     *  |周日|任意行|
     * @param number
     * @return
     */
    public static boolean  isLimit(String number,LocalDate localDate){
        if(localDate == null){
            localDate = LocalDate.now();
        }
        if(localDate.getDayOfWeek().getValue() > 5){
            return false;
        }

        int key = getLastNumber(number);

        if(key == 0){
            key = 10;
        }
        if(key > 5){
            key = key - 5;
        }

        if(localDate.getDayOfWeek().getValue() == key){
            return true;
        }

        return false;
    }


}
