package com.example.login.user;

import android.util.Log;

/**
 * 提交任务，将任务提交到数据库
 */
public class TaskDao extends DBUtil{

    //创建任务，上传提交数据库
    public void createTask(int Tid, String Uid, String Vid, String Ttype, String Ttime, String Tadress, String editTextTextMultiLine){
        //Taskinfo item =null;
        //Log.d("TaskDao", "createTask: 999");
        try{
            getConnection();
            String sql = "insert into Task_List(Tid, Uid, Vid, Ttype, Ttime, Tadress, editTextTextMultiLine) values(?, ?, ?, ?, ?, ?, ?)";
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, Tid);
            pStmt.setString(2, Uid);
            pStmt.setString(3, Vid);
            pStmt.setString(4, Ttype);
            pStmt.setString(5, Ttime);
            pStmt.setString(6, Tadress);
            pStmt.setString(7, editTextTextMultiLine);
            pStmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAll();
        }
        //return false;
    }

    /*
    获取数据库中任务编号的最大值
     */
    public int getMax_ntask(){
        int n=0;
        try{
            getConnection();
            String sql = "select max(Tid) as tid from Task_List";
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while(rs.next())
            {
                n = rs.getInt("tid");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return n;
    }
}
