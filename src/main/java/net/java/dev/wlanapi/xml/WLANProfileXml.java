package net.java.dev.wlanapi.xml;

import javax.xml.bind.annotation.*;

/**
 * @Descirption  WLANProfileXml Bean
 * @Author WuXiang
 * @Date 2018/9/27/
 */

@XmlRootElement(name="WLANProfile")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType( propOrder = {"name","ssidConfig","connectionType","connectionMode","msm"})
public class WLANProfileXml {
    @XmlAttribute( name = "xmlns")
    private String xmlns ="http://www.microsoft.com/networking/WLAN/profile/v1";


    private String name;
    @XmlElement(name = "SSIDConfig")
    private SSIDConfig ssidConfig;

    private String connectionType;


    private String connectionMode;

    @XmlElement(name = "MSM")
    private MSM msm;

    @XmlElement( name = "MacRandomization")
    private MacRandomization macRandomization;

    public MacRandomization getMacRandomization() {
        return macRandomization;
    }

    public void setMacRandomization(MacRandomization macRandomization) {
        this.macRandomization = macRandomization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SSIDConfig getSsidConfig() {
        return ssidConfig;
    }

    public void setSsidConfig(SSIDConfig ssidConfig) {
        this.ssidConfig = ssidConfig;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getConnectionMode() {
        return connectionMode;
    }

    public void setConnectionMode(String connectionMode) {
        this.connectionMode = connectionMode;
    }

    public MSM getMsm() {
        return msm;
    }

    public void setMsm(MSM msm) {
        this.msm = msm;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }
}
