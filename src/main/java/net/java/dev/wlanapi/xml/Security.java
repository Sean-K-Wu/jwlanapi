package net.java.dev.wlanapi.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @Descirption
 * @Author WuXiang
 * @Date 2018/9/27/
 */
@XmlRootElement(name = "security")
@XmlAccessorType(XmlAccessType.FIELD)
public class Security {

    private  AuthEncryption authEncryption;

    private SharedKey sharedKey;

    public AuthEncryption getAuthEncryption() {
        return authEncryption;
    }

    public void setAuthEncryption(AuthEncryption authEncryption) {
        this.authEncryption = authEncryption;
    }

    public SharedKey getSharedKey() {
        return sharedKey;
    }

    public void setSharedKey(SharedKey sharedKey) {
        this.sharedKey = sharedKey;
    }
}
