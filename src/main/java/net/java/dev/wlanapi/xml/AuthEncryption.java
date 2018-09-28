package net.java.dev.wlanapi.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Descirption
 * @Author WuXiang
 * @Date 2018/9/27/
 */
@XmlRootElement(name = "authEncryption")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthEncryption {

    private String  authentication;

    private String encryption;

    private String useOneX;

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public String getUseOneX() {
        return useOneX;
    }

    public void setUseOneX(String useOneX) {
        this.useOneX = useOneX;
    }
}
