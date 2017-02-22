package com.weasel.penetrate.common.helper;

/**
 * @author Dylan
 * @date 2017/1/22.
 */
public final class SystemHelper {


    private static String OS = System.getProperty("os.name").toLowerCase();


    public static boolean isLinux(){
        return OS.indexOf("linux")>=0;
    }

    public static boolean isMacOS(){
        return OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")<0;
    }

    public static boolean isMacOSX(){
        return OS.indexOf("mac")>=0&&OS.indexOf("os")>0&&OS.indexOf("x")>0;
    }

    public static boolean isWindows(){
        return OS.indexOf("windows")>=0;
    }

    public static boolean isOS2(){
        return OS.indexOf("os/2")>=0;
    }

    public static boolean isSolaris(){
        return OS.indexOf("solaris")>=0;
    }

    public static boolean isSunOS(){
        return OS.indexOf("sunos")>=0;
    }

    public static boolean isMPEiX(){
        return OS.indexOf("mpe/ix")>=0;
    }

    public static boolean isHPUX(){
        return OS.indexOf("hp-ux")>=0;
    }

    public static boolean isAix(){
        return OS.indexOf("aix")>=0;
    }

    public static boolean isOS390(){
        return OS.indexOf("os/390")>=0;
    }

    public static boolean isFreeBSD(){
        return OS.indexOf("freebsd")>=0;
    }

    public static boolean isIrix(){
        return OS.indexOf("irix")>=0;
    }

    public static boolean isDigitalUnix(){
        return OS.indexOf("digital")>=0&&OS.indexOf("unix")>0;
    }

    public static boolean isNetWare(){
        return OS.indexOf("netware")>=0;
    }

    public static boolean isOSF1(){
        return OS.indexOf("osf1")>=0;
    }

    public static boolean isOpenVMS(){
        return OS.indexOf("openvms")>=0;
    }

    public static Platform getOSname(){
        if(isAix()){
            return Platform.AIX;
        }else if (isDigitalUnix()) {
            return Platform.Digital_Unix;
        }else if (isFreeBSD()) {
            return Platform.FreeBSD;
        }else if (isHPUX()) {
            return Platform.HP_UX;
        }else if (isIrix()) {
            return Platform.Irix;
        }else if (isLinux()) {
            return Platform.Linux;
        }else if (isMacOS()) {
            return Platform.Mac_OS;
        }else if (isMacOSX()) {
            return Platform.Mac_OS_X;
        }else if (isMPEiX()) {
            return Platform.MPEiX;
        }else if (isNetWare()) {
            return Platform.NetWare_411;
        }else if (isOpenVMS()) {
            return Platform.OpenVMS;
        }else if (isOS2()) {
            return Platform.OS2;
        }else if (isOS390()) {
            return Platform.OS390;
        }else if (isOSF1()) {
            return Platform.OSF1;
        }else if (isSolaris()) {
            return Platform.Solaris;
        }else if (isSunOS()) {
            return Platform.SunOS;
        }else if (isWindows()) {
            return Platform.Windows;
        }else{
            return Platform.Others;
        }
    }

    private SystemHelper(){}


    public static enum Platform {
        Any("any"),
        Linux("Linux"),
        Mac_OS("Mac OS"),
        Mac_OS_X("Mac OS X"),
        Windows("Windows"),
        OS2("OS/2"),
        Solaris("Solaris"),
        SunOS("SunOS"),
        MPEiX("MPE/iX"),
        HP_UX("HP-UX"),
        AIX("AIX"),
        OS390("OS/390"),
        FreeBSD("FreeBSD"),
        Irix("Irix"),
        Digital_Unix("Digital Unix"),
        NetWare_411("NetWare"),
        OSF1("OSF1"),
        OpenVMS("OpenVMS"),
        Others("Others");

        private Platform(String desc){
            this.description = desc;
        }

        public String toString(){
            return description;
        }

        private String description;
    }


}
