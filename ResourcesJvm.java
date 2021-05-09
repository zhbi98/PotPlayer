
package potplayer;


public class ResourcesJvm {
    static void noticeJVM() {
        System.gc();
        System.runFinalization();
        System.gc();
    }
}
