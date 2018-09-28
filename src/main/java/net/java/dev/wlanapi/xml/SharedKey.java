package net.java.dev.wlanapi.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Descirption
 * @Author WuXiang
 * @Date 2018/9/27/
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sharedKey")
public class SharedKey {
    private String keyType;

    @XmlElement( name ="protected")
    private String protect;


    private String keyMaterial;


    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getProtect() {
        return protect;
    }

    public void setProtect(String protect) {
        this.protect = protect;
    }

    public String getKeyMaterial() {
        return keyMaterial;
    }

    public void setKeyMaterial(String keyMaterial) {
        this.keyMaterial = keyMaterial;
    }
}
