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
@XmlRootElement(name ="SSIDConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class SSIDConfig {
    @XmlElement( name = "SSID")
    private SSID ssid;

    public SSID getSsid() {
        return ssid;
    }

    public void setSsid(SSID ssid) {
        this.ssid = ssid;
    }
}
