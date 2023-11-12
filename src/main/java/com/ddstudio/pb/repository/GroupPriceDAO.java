package com.ddstudio.pb.repository;

import com.ddstudio.DBUtil;
import com.ddstudio.pb.model.PriceDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GroupPriceDAO {
    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    public GroupPriceDAO() {

        this.conn = DBUtil.open();
//        System.out.println(this.conn);

    }


    public int add(PriceDTO dto) { // 티켓 테이블 추가하기

        //DTO > insert

        try {

            String sql = "insert into TBLTICKET (ticket_seq, ticket_type, person_type, age, price) values (SEQTBLTICKET.nextval, ?, '단체', ?, ?)";

            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getTicket_type());
            pstat.setString(2, dto.getAge());
            pstat.setInt(3, dto.getPrice());

            int result = pstat.executeUpdate();



            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }



        return 0;

    }
    public ArrayList<PriceDTO> groupList() {  //티켓테이블 개인/단체 에서 단체꺼만 가져오기

        ArrayList<PriceDTO> list = new ArrayList<>();


        try {

            String sql = "select * from TBLTICKET where PERSON_TYPE ='단체' order by PRICE desc ";

            stat = conn.createStatement();

            rs = stat.executeQuery(sql);

            //rs == 메모 목록

            //rs를  list로 옮기기
            while (rs.next()) {

                //레코드 1줄 > MemoDTO 1개
                PriceDTO dto = new PriceDTO();
                dto.setTicket_seq(rs.getString("ticket_seq"));
                dto.setTicket_type(rs.getString("ticket_type"));
                dto.setPerson_type(rs.getString("person_type"));
                dto.setAge(rs.getString("age"));
                dto.setPrice(rs.getInt("price"));


                list.add(dto);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }
    public ArrayList<PriceDTO> ticketTypeList() {

        ArrayList<PriceDTO> list = new ArrayList<>();


        try {

            String sql = "select distinct TICKET_TYPE  from TBLTICKET where PERSON_TYPE = '단체'";  // 종일권인지 , 4시이후권인지

            stat = conn.createStatement();

            rs = stat.executeQuery(sql);

            //rs == 메모 목록

            //rs를  list로 옮기기
            while (rs.next()) {

                //레코드 1줄 > MemoDTO 1개
                PriceDTO dto = new PriceDTO();
                dto.setTicket_type(rs.getString("ticket_type"));


                list.add(dto);
            }


        } catch (Exception e) {
            e.printStackTrace();

        }

        return list;

    }

    public ArrayList<PriceDTO> ageList() { //  나이대 가져오기 (성인,청소년,어린이)

        ArrayList<PriceDTO> list = new ArrayList<>();


        try {

            String sql = "select distinct age  from TBLTICKET where PERSON_TYPE = '단체'";  // 종일권인지 , 4시이후권인지

            stat = conn.createStatement();

            rs = stat.executeQuery(sql);

            //rs == 메모 목록

            //rs를  list로 옮기기
            while (rs.next()) {

                //레코드 1줄 > MemoDTO 1개
                PriceDTO dto = new PriceDTO();
                dto.setAge(rs.getString("age"));


                list.add(dto);
            }

            stat.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;

    }

    public int edit(PriceDTO dto) {

        try {

            String sql = "update TBLTICKET set PRICE = ? where TICKET_TYPE = ? and AGE = ? and PERSON_TYPE = '단체'";

            pstat = conn.prepareStatement(sql);

            pstat.setInt(1, dto.getPrice());
            pstat.setString(2, dto.getTicket_type());
            pstat.setString(3, dto.getAge());

            int result =  pstat.executeUpdate();


            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int del(PriceDTO dto) {
        try {

            String sql = "delete from TBLTICKET where TICKET_TYPE = ? and AGE = ? and PERSON_TYPE = '단체'";

            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getTicket_type());
            pstat.setString(2, dto.getAge());

            int result =  pstat.executeUpdate();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
