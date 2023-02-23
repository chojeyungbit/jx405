package controller;

import connector.ConnectionMaker;
import model.ReplyDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReplyController {
    private Connection connection;

    public ReplyController(ConnectionMaker connectionMaker) {
        this.connection = connectionMaker.makeConnection();
    }

    public void insert(ReplyDTO replyDTO) {
        String query = "INSERT INTO `reply`(`content`, `board_id`, `writer_id`, `entry_date`) VALUES(?, ?, ?, NOW())";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, replyDTO.getContent());
            pstmt.setInt(2, replyDTO.getBoardId());
            pstmt.setInt(3, replyDTO.getWriterId());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ReplyDTO> selectAll(int boardId) {
        ArrayList<ReplyDTO> temp = new ArrayList<>();
        String query = "SELECT * FROM `reply` WHERE `board_id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, boardId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                ReplyDTO r = new ReplyDTO();
                r.setId(resultSet.getInt("id"));
                r.setContent(resultSet.getString("content"));
                r.setBoardId(resultSet.getInt("board_id"));
                r.setWriterId(resultSet.getInt("writer_id"));
                r.setEntryDate(resultSet.getTimestamp("entry_date"));
                r.setModifyDate(resultSet.getTimestamp("modify_date"));

                temp.add(r);
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return temp;
    }

    public ReplyDTO selectOne(int id) {
        ReplyDTO r = null;
        String query = "SELECT * FROM `reply` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                r = new ReplyDTO();
                r.setId(resultSet.getInt("id"));
                r.setContent(resultSet.getString("content"));
                r.setBoardId(resultSet.getInt("board_id"));
                r.setWriterId(resultSet.getInt("writer_id"));
                r.setEntryDate(resultSet.getTimestamp("entry_date"));
                r.setModifyDate(resultSet.getTimestamp("modify_date"));
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }

    public void update(ReplyDTO replyDTO) {
        String query = "UPDATE `reply` SET `content` = ?, `modify_date` = NOW() WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, replyDTO.getContent());
            pstmt.setInt(2, replyDTO.getId());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        String query = "DELETE FROM `reply` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
