package com.yanqun.dao;

/*
 * Created by 颜群
 */
public interface AddressDao
//        extends JpaRepository<Address,String>, JpaSpecificationExecutor<Address>
{

    /*查询每一个地址，各自有多少人使用
               xa      3
               bj      5
             核心：将原生的SQL，放在value中。无论是 单表、还是复杂的多表查询都可以。
        */
//    @Query(nativeQuery = true ,value="select a.name,count(1) from tb_address a inner join tb_people p on a.id = p.address group by a.name")
//    public List<Object[]> findAddressNamesAndCount() ;
//
//    //DML
//    @Modifying
//    @Query(nativeQuery = true ,value="insert into tb_address(id,name) values(?,?)")
//    public int addAddress(Integer id ,String name);
//
//    @Modifying
//    @Query(nativeQuery = true,value="delete from tb_address where id = ?")
//    public int deleteAddressById(Integer id);
//
//    @Modifying
//    @Query(nativeQuery = true,value = "update tb_address a set a.name= ? where a.id =?")
//    public int updateAddress( String name , Integer id );
}
