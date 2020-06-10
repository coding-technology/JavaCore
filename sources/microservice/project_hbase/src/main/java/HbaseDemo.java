import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

/*
 * Created by 颜群
 */
public class HbaseDemo {

    //读取配置文件
    static Configuration cfg = HBaseConfiguration.create() ;
    //查看所有表 list
    public static void list() throws Exception {
        Connection connection = ConnectionFactory.createConnection(cfg);
        Admin admin = connection.getAdmin();
        TableName[] tableNames = admin.listTableNames();
        for(TableName name: tableNames){
            System.out.println(name);
        }
        connection.close();
    }

    //create  tableName , a,b,c,...
    public static void create(String tableName,String ...familyNames) throws Exception{
        Connection connection = ConnectionFactory.createConnection(cfg);
        Admin admin = connection.getAdmin();
        TableName tn = TableName.valueOf(tableName);
        if(admin.tableExists( tn )){//如果已经存在，则删除
            admin.disableTable(tn);
            admin.deleteTable(tn);
        }
        //创建表  表：增加1个列族、增加1个列族、增加1个列族
        HTableDescriptor htd = new HTableDescriptor(tn);
        for(String family:familyNames){
                    htd.addFamily( new HColumnDescriptor( family)    )  ;
        }
        admin.createTable(htd);
        connection.close() ;
    }

//    //修改表结构：增加列族
//    public static void addColumnFamily(String tableName,String ...familyNames) throws Exception {
//        Connection connection = ConnectionFactory.createConnection(cfg);
//        Admin admin = connection.getAdmin();
//        TableName tn = TableName.valueOf(tableName);
//        HTableDescriptor htd = new HTableDescriptor(tn);
//        for(String family:familyNames){
//            htd.addFamily( new HColumnDescriptor( family)    )  ;
//        }
////        admin.createTable(htd);
//        admin.modifyTable( tn,htd );
//        connection.close() ;
//    }
    //修改表结构：增加列族
    public static void addColumnFamily(String tableName,String ...familyNames) throws Exception {
        Connection connection = ConnectionFactory.createConnection(cfg);
        Admin admin = connection.getAdmin();
        TableName tn = TableName.valueOf(tableName);

        HTableDescriptor htd = admin.getTableDescriptor(tn);


        for(String family:familyNames){
            htd.addFamily( new HColumnDescriptor( family)    )  ;
        }
//        admin.createTable(htd);
        admin.modifyTable( tn,htd );
        connection.close() ;
    }
    //修改表结构：删除出列族
    public static void removeColumnFamily(String tableName,String ...familyNames) throws Exception {
        Connection connection = ConnectionFactory.createConnection(cfg);
        Admin admin = connection.getAdmin();
        TableName tn = TableName.valueOf(tableName);

        HTableDescriptor htd =admin.getTableDescriptor(tn);

        for(String family:familyNames){
//            htd.addFamily( new HColumnDescriptor( family)    )  ;
            htd.removeFamily(Bytes.toBytes(family )    ) ;
        }
//        admin.createTable(htd);
        admin.modifyTable( tn,htd );
        connection.close() ;
    }



    //查看表结构
    public static void desc(String tableName) throws Exception {
        Connection connection = ConnectionFactory.createConnection(cfg);
        Admin admin = connection.getAdmin();
        TableName tn = TableName.valueOf(tableName);
        HTableDescriptor htd = admin.getTableDescriptor(tn);
        System.out.println("表结构");
        for(HColumnDescriptor hcd:    htd.getColumnFamilies()){
            System.out.println(   hcd.getNameAsString()  );

        }
        connection.close();

    }




    //增加数据
    public static void put(String tableName) throws Exception {
        Connection connection = ConnectionFactory.createConnection(cfg);
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put p = new Put(Bytes.toBytes( "zs" )  );

        //grade:         2
        p.addColumn(  Bytes.toBytes(  "grade")  ,Bytes.toBytes("")  , Bytes.toBytes("2") ) ;
        //course:math   100
        p.addColumn(Bytes.toBytes("course"),Bytes.toBytes("math"),Bytes.toBytes("88")) ;

        table.put(p);
        connection.close() ;
    }

    //查询一行数据
    public static void getRow(String tableName,String rowKey) throws Exception {
        Connection connection = ConnectionFactory.createConnection(cfg); ;

        Table tb = connection.getTable(TableName.valueOf(tableName)) ;
        Get g = new Get(  Bytes.toBytes(rowKey) ) ;

        Result result = tb.get(g);
//        System.out.println(   Bytes.toString( result.value() )       );
        System.out.println(String.format("result.value=%s ,result.toString():%s", Bytes.toString(result.value()),result));
        connection.close();
    }

    //scan
    public static void scan(String tableName) throws Exception{
        Connection connection = ConnectionFactory.createConnection(cfg);
        Table tb = connection.getTable(TableName.valueOf(tableName));
        ResultScanner scanner = tb.getScanner(new Scan());
        for( Result row:  scanner){
            System.out.println("每一行数据:"+row);

            for(Cell cell:row.listCells()){
                System.out.println(
                        "rowskey:"+  Bytes.toString(  row.getRow()  )
                                +
                                "family:"  +Bytes.toString(  CellUtil.cloneFamily(cell))
                        +
                                "value:" +Bytes.toString(  CellUtil.cloneValue(cell))

                );
            }
        }
    }


        public static void main(String[] args) throws Exception {
//        create("scores","grade","course") ;
//        addColumnFamily("scores","x","y");
//        removeColumnFamily("scores","a");
//        list() ;
//        desc("scores") ;
//        put("scores");
//        getRow("scores","zs");
          scan("scores");
    }

}
