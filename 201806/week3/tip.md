# spring基础知识复习要点
1. Spring 依赖注入
Setter注入、构造函数注入、（避免循环依赖）

2. Spring的自动装配有三种模式：byTpye(根据类型)，byName(根据名称)、constructor(根据构造函数)。
@Autowired是按类型匹配的(byType)，如果需要按名称(byName)匹配的话，可以使用@Qualifier
@Autowired @Qualifier("userDao") = @Resource(name=“userDao”)

3. @Value注解的自动装配以及properties文件读取

4. @Component与@Service和@Repository相同效果
@Component与@Service的含义并无差异，只不过@Service更能让我们明白该类为业务类罢了。
@Repository在表示数据访问层含义的同时还能够启用与Spring数据访问相关链的其他功能

5. Bean的重写机制：当声明的bean的名称一样时，后者会覆盖前者

6. Bean的作用域：默认Singleton，prototype作用域
一般情况下，对有状态的bean应该使用prototype作用域，而对无状态的bean则应该使用singleton作用域。
所谓有状态就是该bean有保存信息的能力，不能共享，否则会造成线程安全问题，
而无状态则不保存信息，是线程安全的，可以共享，spring中大部分bean都是Singleton，整个生命周期过程只会存在一个。

7. request与session作用域
关于request作用域，对于每次HTTP请求到达应用程序，Spring容器会创建一个全新的Request作用域的bean实例，
且该bean实例仅在当前HTTP request内有效，整个请求过程也只会使用相同的bean实例，因此我们可以根据需要放心的更改所建实例的内部状态，
而其他请求HTTP请求则创建新bean的实例互不干扰，当处理请求结束，request作用域的bean实例将被销毁，
如在接收参数时可能需要一个bean实例来装载一些参数，显然每次请求参数几乎不会相同，
因此希望bean实例每次都是足够新的而且只在request作用域范围内有效。
关于Session可能你也已猜到，每当创建一个新的HTTP Session时就会创建一个Session作用域的Bean，并该实例bean伴随着会话的存在而存在。

IOC 与依赖注入的区别
IOC：控制反转:将对象的创建权,由Spring管理。

DI(依赖注入)：在Spring创建对象的过程中,把对象依赖的属性注入到类中。


