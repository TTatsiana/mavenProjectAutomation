package moduletwo10.framework.loger;

import org.apache.log4j.Logger;

public class Log {

    public static Logger logger=Logger.getLogger("MyLogger");

    public static void debug(String mess){
        logger.debug(mess);
    }

    public static void info(String mess){
        logger.info(mess);
    }

    public static void error(String mess){
        logger.error(mess);
    }
}
