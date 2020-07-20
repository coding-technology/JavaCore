package com.yanqun.dao;

/*
 * Created by 颜群
 * jpa使用方式一：继承JpaRepository<People,String> , JpaSpecificationExecutor<People>;需要写任何代码
 *
 * JpaRepository ：提供了基本的增删改查
 * JpaSpecificationExecutor：较为复杂的条件查询
 */
public interface PeopleDao
//        extends JpaRepository<People,String> , JpaSpecificationExecutor<People>
{
//    //jpa使用方式一：dao层中不用自己写任何方法，只调用父接口中的方法。缺点：父接口中提供的方法有限
//    //jpa使用方式二：可以编写自己的方法，但是可以不写SQL。根据约定写方法的名字。
//    public List<People> findByNameOrderByAgeDesc(String name) ;
//
//    //jpa使用方式三：面向对象SQL语句 ：JPQL
//    //select p from People p from where 类中的属性 = ?序号
//    @Query("select p from People p where age =?1 ")
//    public List<People> findByAgeAsc(Integer age) ;//因为上面是通过Query指定了HQL，因此方法名可以任意编写
//
//    //在方式三的基础上，加上分页功能
//    @Query("select p from People p where age =?1 ")
//    public List<People> findByAgeAsc2(Integer age , Pageable pageable) ;//因为上面是通过Query指定了HQL，因此方法名可以任意编写
//
//    @Modifying
//    @Query("update People  set age=age+1 where id =?1")
//    public int updatePeople(String id) ;
//
//    @Modifying
//    @Query("update People  set age=?2 where id =?1")
//    public int updatePeople2( String id,Integer age);
//
//
//    @Modifying
//    @Query("update People p set  p.age = :#{#people.age}  , p.address=:#{#people.address}   where p.id =:#{#people.id}")
//    public int updatePeople3(@Param("people") People people );//28  ,shanghai
//
//    //jpa使用方式四：面向SQL（原生的SQL语句）,推荐
//    @Query(nativeQuery = true , value="select  name from tb_people where id =? ")
//    public String findNameById(String id) ;
//
//    @Query(nativeQuery = true,value="select * from tb_people where name = ? and age =? ")
//    public List<People> findPeopleByNameAndAge(String name ,int age);
//
//    @Query(nativeQuery = true,value="select * from tb_people where age in (:ages) order by age")
//    public Page<People> findPeopleByAges(@Param("ages") List<Integer> ages   , Pageable pageable);
    //public  List<People> findPeopleByAges(@Param("ages") List<Integer> ages   , Pageable pageable);

    //jpa使用方式五：面向对象查法
    /*
            两类：
                    方式一、方式二、方式五： 用JPA底层内部提供的方式（不需要写SQL、JPQL）
                            JPA对方式一、方式二 提供的支持优先，只能实现一些简单的CRUD；如果要实现复杂功能，则需要使用方式五
                            方式五的思路，类似于mybatis逆向工程。设计理念：可以让不懂SQL的人，写出复杂的SQL语句
                    方式三、方式四：    自己需要写SQL、JPQL



                    方式五实现步骤：
                            类似于方式一，不用在DAO中自定义任何方法（要写DAO,只是不用写DAO中的方式）。
                            实现service、controller


     */



}
