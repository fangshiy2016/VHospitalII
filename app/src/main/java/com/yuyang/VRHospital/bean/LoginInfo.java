package com.yuyang.VRHospital.bean;

/**
 * Created by fanshy on 2016/7/3.
 */

import java.util.List;

/**
 * 登录信息
 *
 * @author CreditEase/FSO
 *
 */
public class LoginInfo {

    //{"code":"test001","floderID":7,"name":"test001","passWord":"111111","userType":"","userGroupCode":"tuijianzhuangjia",
    // "userGroupName":"A类专家","age":12,"gender":0,"country":"中国","countryCode":"86","aeraProvince":"北京市",
    // "aeraProvinceCode":"110000","aeraCity":"北京市","aeraCityCode":"110100","aeraZone":"东城区","aeraZoneCode":"110101",
    // "title":"教授","titleCode":"jiaoshou","hospitalTitle":"test001","profession":"妇科","professionCode":"fuke",
    // "description":"test001","accessTypeCode":"'tuijianzhuanjia','huizhenzhuanjia','zaixianzhuanjia','zishenzhuanjia','1'","mediacalRecords":"'HPVJL1','HPVJL1JL_HPV_0001'","mediacalRecordsText":"'HPV结论','HPV结论HPV筛查结论9-21岁'"}
    String code;
    int    floderID;
    String name;
    String passWord;
    int    userType;
    String userGroupCode;
    String userGroupName;
    int    age;
    int    gender;
    String country;
    String countryCode;
    String aeraProvince;
    String aeraProvinceCode;
    String aeraCity;
    String aeraCityCode;
    String aeraZone;
    String aeraZoneCode;
    String title;
    String titleCode;
    String jiaoshou;
    String hospitalTitle;
    String profession;
    String professionCode;
    String description;
    //String[] accessTypeCode;
    //String[] mediacalRecords;
    //String[] mediacalRecordsText;
    List<zhenDuanTemplate> zhenDuanTemplates;
    String  headerImage;
    String  tel;
    String  deviceNumber;
    int     isActivation;
    //tel":"","deviceNumber":"","isActivation":0

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFloderID() {
        return floderID;
    }

    public void setFloderID(int floderID) {
        this.floderID = floderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserGroupCode() {
        return userGroupCode;
    }

    public void setUserGroupCode(String userGroupCode) {
        this.userGroupCode = userGroupCode;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAeraProvince() {
        return aeraProvince;
    }

    public void setAeraProvince(String aeraProvince) {
        this.aeraProvince = aeraProvince;
    }

    public String getAeraProvinceCode() {
        return aeraProvinceCode;
    }

    public void setAeraProvinceCode(String aeraProvinceCode) {
        this.aeraProvinceCode = aeraProvinceCode;
    }

    public String getAeraCity() {
        return aeraCity;
    }

    public void setAeraCity(String aeraCity) {
        this.aeraCity = aeraCity;
    }

    public String getAeraCityCode() {
        return aeraCityCode;
    }

    public void setAeraCityCode(String aeraCityCode) {
        this.aeraCityCode = aeraCityCode;
    }

    public String getAeraZone() {
        return aeraZone;
    }

    public void setAeraZone(String aeraZone) {
        this.aeraZone = aeraZone;
    }

    public String getAeraZoneCode() {
        return aeraZoneCode;
    }

    public void setAeraZoneCode(String aeraZoneCode) {
        this.aeraZoneCode = aeraZoneCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    public String getJiaoshou() {
        return jiaoshou;
    }

    public void setJiaoshou(String jiaoshou) {
        this.jiaoshou = jiaoshou;
    }

    public String getHospitalTitle() {
        return hospitalTitle;
    }

    public void setHospitalTitle(String hospitalTitle) {
        this.hospitalTitle = hospitalTitle;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfessionCode() {
        return professionCode;
    }

    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<zhenDuanTemplate> getZhenDuanTemplates() {
        return zhenDuanTemplates;
    }

    public void setZhenDuanTemplates(List<zhenDuanTemplate> zhenDuanTemplates) {
        this.zhenDuanTemplates = zhenDuanTemplates;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public int getIsActivation() {
        return isActivation;
    }

    public void setIsActivation(int isActivation) {
        this.isActivation = isActivation;
    }

    /*int id;
        String code;
        String address;
        String name;
        String passWord;
        String status;
        String tel;
        int sex;
        String birth;
        String card;
        String type;
        String weiXin;
        String qq;
        String email;
        String desc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassWord() {
            return passWord;
        }

        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWeiXin() {
            return weiXin;
        }

        public void setWeiXin(String weiXin) {
            this.weiXin = weiXin;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }*/
}