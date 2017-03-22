package cn.lib.base;
/**  
 *  全局控制类
 *  
 *  @author  wangdeshun
 *  1、由于单利模式中没有抽象层，因此单例类的扩展有很大的困难。
 *  2、单例类的职责过重，在一定程度上违背了“单一职责原则”。
 *  3、如果实例化的对象长时间不被利用，系统会认为是垃圾而被回收，这将导致对象状态的丢失。
 *
 *  这种写法仍然使用JVM本身机制保证了线程安全问题；由于SingletonHolder是私有的，除了getInstance()之外没有办法访问它，
 *  因此它是懒汉式的；同时读取实例的时候不会进行同步，没有性能缺陷；也不依赖JDK版本。
 *
 *  @version 1.0 
 */
public class AppHolder {
    /**
     * 单例变量 直接new 一个对象传递给类的成员变量
     */
    private static final AppHolder instance = new AppHolder();

    /**
     * 私有化的构造方法，保证外部的类不能通过构造器来实例化。
     */
    private AppHolder() {
    	
    }

    /**
     * 获取单例对象实例 限制住不能直接产生一个实例
     * @return
     */
    public synchronized static AppHolder getInstance() {
        return instance;
    }
}
