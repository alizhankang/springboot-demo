package com.lizhankang.springbootdemo.config;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;
import java.util.UUID;


public class ApiUtils {
    public static void main(String[] args) {
//        String dateTime = ApiUtils.getDateTime();
//        System.out.println(dateTime);
//        System.out.println(readPrivateKey());
    }

    /**
     * 获取指定格式的当前时间
     * @return  2023-09-11T22:48:10+08:00[String]
     */
    public static String getNowDateTime() {
        Date date = new Date();
//        System.out.println(date.toString());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
//        String sales_time1 = formatter.format(date);
        return formatter.format(date);
    }

    /**
     * 获取 **天后的当前时间
     */
    public static String getSomeDaysLaterDateTime(long days) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime futureDateTime = currentDateTime.plus(days, ChronoUnit.DAYS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        // 设置时区为+08:00（中国时区）
        ZoneId chinaZone = ZoneId.of("Asia/Shanghai");
        String formattedDate = futureDateTime.atZone(chinaZone).format(formatter);

        return formattedDate;
    }


    public static String getSomeHoursLaterDateTime(long hours) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime futureDateTime = currentDateTime.plus(hours, ChronoUnit.HOURS);
        // 指定日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        // 设置时区为+08:00（中国时区）
        ZoneId chinaZone = ZoneId.of("Asia/Shanghai");
        String formattedDate = futureDateTime.atZone(chinaZone).format(formatter);

        return formattedDate;
    }

    /**
     * 获取 **分钟后 的时间点
     * @param minutes
     * @return
     */
    public static String getSomeMinutesLaterDateTime(long minutes) {
        // 获取当前时间
        LocalDateTime currentDateTime = LocalDateTime.now();
        // 添加30天
        LocalDateTime futureDateTime = currentDateTime.plus(minutes, ChronoUnit.MINUTES);
        // 指定日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        // 设置时区为+08:00（中国时区）
        ZoneId chinaZone = ZoneId.of("Asia/Shanghai");
        String formattedDate = futureDateTime.atZone(chinaZone).format(formatter);
        return formattedDate;
    }

    /**
     * 获取指定长度的数字字符串
     */
    public static String getNumberString(int length) {
        if (length <= 0) {
            return "";
        }
        StringBuilder numberString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // 生成0到9之间的随机数字
            numberString.append(digit);
        }
        return numberString.toString();
    }

    /**
     * 获取requestId
     * @return
     */
    public static String getReuestId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    /**
     * 获取指定长度的requestId
     * @param len
     * @return
     */
    public static String getReuestId(int len){

        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString().replace("-", "");
        return uuidString.substring(0, len);
    }


    /**
     * 将第三方接口返回体转化成需要的Java对象
     */
//    public static KaPurchaseResponse parseToPurchaseResponse(String thirdApiResponseJson){
////        String thirdApiResponseJson = "{'name': 'John', 'age': 30, 'email': 'john@example.com'}";
//
//        // 创建ObjectMapper对象
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            // 使用ObjectMapper将JSON字符串转换为ThirdPartyResponse对象
//            KaPurchaseResponse kaPurchaseResponse = objectMapper.readValue(thirdApiResponseJson, KaPurchaseResponse.class);
//            System.out.println(kaPurchaseResponse.getResponse());
//            return kaPurchaseResponse;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    /**
     * 读取密钥
     */

    public static String getPrivateKey(String privateKeyPath){
        String privateKeyBase64 = "";
        Security.addProvider(new BouncyCastleProvider());

//        String privateKeyFilePath = "/Users/sqb/all-shouqianba-projects/python-projects/ka-request/common/privateKey.pem"; // 替换为你的私钥文件路径
        try {
            BufferedReader reader = new BufferedReader(new FileReader(privateKeyPath));
            PEMParser pemParser = new PEMParser(reader);

            Object object = pemParser.readObject();
            if (object instanceof PEMKeyPair) {
                PEMKeyPair keyPair = (PEMKeyPair) object;

                // 获取ASN.1编码的私钥数据
                byte[] privateKeyData = keyPair.getPrivateKeyInfo().getEncoded();
                // 将私钥数据转换为Base64编码的字符串
                privateKeyBase64 = java.util.Base64.getEncoder().encodeToString(privateKeyData);
            }
            pemParser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return privateKeyBase64;
    }
}