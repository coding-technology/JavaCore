package com.yanqun.service;

import com.yanqun.dao.PeopleDao;
import com.yanqun.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Created by 颜群
 */

@Service
public class PeopleService {
    @Autowired
    private PeopleDao peopleDao ;


    public void savePeople(People people){
        peopleDao.save(people) ;
    }


    //jpa底层，修改和 增加都是 save()
    public void updatePeople(People people){
            peopleDao.save( people) ;
    }

    public void deletePeopleById(String id){
        peopleDao.deleteById(id);
    }

    public List<People> findAll() {
       return  peopleDao.findAll() ;
    }

    public People findPeopleById(String id){
        return peopleDao.findById( id ).get() ;
    }

    public List<People> findByNameOrderByAgeDesc(String name) {
      return   peopleDao.findByNameOrderByAgeDesc(name) ;
    }

    public List<People> findByAgeAsc(Integer age) {
        return   peopleDao.findByAgeAsc(age) ;
    }

    public List<People> findByAgeAsc2(Integer age,int currentPage ,int pageSize) {
//        PageRequest.of(第几页，页面大小);
        PageRequest pageRequest = PageRequest.of(currentPage-1, pageSize);//1  ,0
        return   peopleDao.findByAgeAsc2(age, pageRequest) ;
    }

    @Transactional
    public int updatePeople(String id){
       return  peopleDao.updatePeople(id) ;
    }



//    @Transactional
    public int updatePeople2(String id,int age){
        return  peopleDao.updatePeople2(id,age) ;
    }



    @Transactional
    public int updatePeople3(People people){
        return  peopleDao.updatePeople3(people) ;
    }


    public String findNameById(String id) {
        return peopleDao.findNameById(id) ;
    }

    public List<People> findPeopleByNameAndAge(String name,int age){
        return peopleDao.findPeopleByNameAndAge(name,age) ;
    }


    public Page<People> findPeopleByAges(List<Integer> ages, Integer currentPage, Integer pageSize){
        PageRequest pageRequest = PageRequest.of(currentPage-1, pageSize);
        return peopleDao.findPeopleByAges(ages,pageRequest) ;
    }


    //map [ {id,1}, {name,zs}   ]
    //select *from people where id =?  and name like '%?%'  and age=?  and address=?
    //方式五
    public Page<People> findPeople(Map<String,Object> map, int currentPage,int pageSize){

        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);

        return peopleDao.findAll(new Specification<People>() {
            @Override
            public Predicate toPredicate(Root<People> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //查询条件
                List<Predicate>  predicates = new ArrayList<>() ;
                if (map.get("age") != null  &&  map.get("age")!=""  ) {//是否where子句中有age
                    predicates.add( criteriaBuilder.equal(  root.get("age").as(Integer.class)  ,(Integer)map.get("age") )          ) ;
                }

                if(map.get("name") != null  &&  map.get("name")!=""){
                    predicates.add(  criteriaBuilder.like( root.get("name").as(String.class)  ,"%"+(String)map.get("name")+"%" )  );
                }


                //if  address
                //if  age
                //API拼凑
                return  criteriaBuilder.and( predicates.toArray( new Predicate[predicates.size()] )  );
            }
        }, pageRequest     ) ;



    }








}
