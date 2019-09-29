package com.manager.book;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BookManager {
    private Book[] books;
    private Scanner input = new Scanner(System.in);

    //初始化方法:图书馆成立的时候，预先给里面增加一些书（一共能够容纳10本书， 成立时 3本）
    public void init() {
        books = new Book[10];
        // {x,x,x,null,null,...null}

        Book book0 = new Book();
        book0.setId(1000);
        book0.setName("tijava");
        book0.setState(1);//在库
//        book0.setBorrowDate(null);
//        book0.setBorrowCount(0);
        Book book1 = new Book();//shift+f6
        book1.setId(1001);
        book1.setName("高并发");
        book1.setState(1);//在库

        Book book2 = new Book();//shift+f6
        book2.setId(1002);
        book2.setName("分布式");
        book2.setState(1);//在库

        books[0] = book0;
        books[1] = book1;
        books[2] = book2;
    }


    //菜单
    public void menu() {


        System.out.println("======欢迎进入图书管理系统========");
        System.out.println("请根据提示选择");//1
        System.out.println("1.增加图书");
        System.out.println("2.删除图书");
        System.out.println("3.借阅图书");
        System.out.println("4.归还图书");
        System.out.println("5.图书风云榜");
        System.out.println("6.查看图书");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                deleteBook();
                break;
            case 3:
                borrowBook();
                break;
            case 4:
                returnBook();
                break;
            case 5:
                bookRank();
                break;
            case 6:
                showBooks();
                break;
            default:
        }


    }

    public void isContinueMenu() {
        System.out.println("是否回到主菜单其他操作(y/其他)");
        String isContinue = input.next();
        if ("y".equals(isContinue)) {
            menu();//递归调用
        }
    }

    private void addBook() {
        System.out.println("增加图书...");
        Book book = new Book();

        System.out.println("请输入书的编号");
        int id = input.nextInt();
        System.out.println("请输入书的名字");
        String name = input.next();

        int state = 1;
//        Date bDate = null ;
//        int bCount = 0 ;

        book.setId(id);
        book.setName(name);
        book.setState(1);

//        book.setBorrowDate(null);

        // {x,x,x,null,null,...null}
        //寻找增加的位置：第一个为null的位置
        int position = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                position = i;
                break;
            }
        }
        System.out.println(position);
        books[position] = book;
        isContinueMenu();
    }


    // {x(1,java),x(2,c),x(3,html),x(4,python),null,null,...null}
    //根据书名删除
    private void deleteBook() {
        System.out.println("请输入要删除的书名");//c
        String name = input.next();
        //定位要删除的书的位置
        int position = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getName().equals(name)) {
                position = i;
                break;
            }
        }

        //删除：移动（start ,end）
        int start = position + 1;
        int firstNullPosition = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                firstNullPosition = i;
                break;
            }
        }
        int end = firstNullPosition - 1;


        if (firstNullPosition == -1) {//书架已满，不存在为null的元素
            end = books.length - 1;
        }

        //移动
        for (int i = start; i <= end; i++) {
            books[i - 1] = books[i];
        }

        books[end] = null;

        isContinueMenu();
    }

    // {x(1,java),x(2,c),x(3,html),x(4,python),null,null,...null}
    private void borrowBook() {
        System.out.println("借阅图书");
        System.out.println("请输入要借阅书的名字");
        String name = input.next();

        int position = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getName().equals(name)) {
                position = i;
                break;
            }
        }

        books[position].setState(0);
//        books[position].setState(当时的时间);

        Date date = new Date();
        books[position].setBorrowDate(date);
        isContinueMenu();
    }

    // {x(1,java),x(2,c),x(3,html),x(4,python),null,null,...null}
    private void returnBook() {
        System.out.println("归还图书");
        System.out.println("请输入要归还书的名字");
        String name = input.next();
        int position = -1;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getName().equals(name)) {
                position = i;
                break;
            }
        }
        books[position].setState(1);
        books[position].setBorrowDate(null);//2019-05-11 ->null


        int count = books[position].getBorrowCount() + 1;
        books[position].setBorrowCount(count);


        isContinueMenu();
    }

    //根据被借次数降序
    private void bookRank() {
        System.out.println("风云榜");//排序：冒泡排序 1  2 3  5  10
        //排序 应该是一个临时操作，而不是永久
        Book[] newBooks = new Book[books.length];

        //对象之间 直接赋值：赋值的是 内存地址（引用）
//        newBooks = books ;//非8个基本类型，其他全是 引用类型（赋值的是 内存地址 ）
        for (int i = 0; i < books.length; i++) { //10000   3
            if (books[i] != null) {
                newBooks[i] = books[i];
            }
        }

        for (int i = 0; i < newBooks.length - 1; i++) {
            for (int j = 0; j < newBooks.length - 1 - i; j++) {
                if (newBooks[j] != null && newBooks[j + 1] != null) {//{x,x,x,null,null}
                    if (newBooks[j].getBorrowCount() < newBooks[j + 1].getBorrowCount()) {
//                  交换
                        Book temp = newBooks[j];
                        newBooks[j] = newBooks[j + 1];
                        newBooks[j + 1] = temp;
                    }
                }
            }
        }

//        showBooks() ;

        System.out.println("编号\t\t\t书名\t\t\t库存\t\t被借日期\t\t被借次数");
        for (Book book : newBooks) {
            if (book != null) {
//                String borrowDateStr =  book.getBorrowDate() == null ? "-" :book.getBorrowDate()+"" ;
                String borrowDateStr = "";
                if (book.getBorrowDate() == null) {
                    borrowDateStr = "-";
                } else {
//                    borrowDateStr =  book.getBorrowDate()+"" ;
                    Date bDate = book.getBorrowDate();
                    //Date -> String
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    borrowDateStr = sdf.format(bDate);

                }
                String stateStr = book.getState() == 0 ? "被借" : "在库";


                System.out.println(book.getId() + "\t\t" + book.getName() + "\t\t" + stateStr + "\t\t" + borrowDateStr + "\t\t" + book.getBorrowCount());
            }
        }


        isContinueMenu();
    }

    //{x,x,x,null,null,...,null}   null.getId() -->空指针异常
    //查看所有的书列表
    private void showBooks() {


        System.out.println("编号\t\t\t书名\t\t\t库存\t\t被借日期\t\t被借次数");
        for (Book book : books) {
            if (book != null) {
//                String borrowDateStr =  book.getBorrowDate() == null ? "-" :book.getBorrowDate()+"" ;
                String borrowDateStr = "";
                if (book.getBorrowDate() == null) {
                    borrowDateStr = "-";
                } else {
//                    borrowDateStr =  book.getBorrowDate()+"" ;
                    Date bDate = book.getBorrowDate();
                    //Date -> String
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    borrowDateStr = sdf.format(bDate);

                }
                String stateStr = book.getState() == 0 ? "被借" : "在库";


                System.out.println(book.getId() + "\t\t" + book.getName() + "\t\t" + stateStr + "\t\t" + borrowDateStr + "\t\t" + book.getBorrowCount());
            }
        }

        isContinueMenu();
    }


}

/*

    录入50个学生信息（姓名  年龄  成绩）
    自动打印出 成绩前3的学生信息（风云榜）




 */
