package dbController;

import model.BoardDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class BoardController {
    private Connection connection;

    public BoardController(Connection connection) {
        this.connection = connection;
    }

    public void insert(BoardDTO boardDTO) {
        String query = "INSERT INTO `board`(`title`, `content`, `writerId`, `entry_date`, `modify_date`) " +
                "VALUES(?, ?, ?, NOW(), NOW())";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, boardDTO.getTitle());
            pstmt.setString(2, boardDTO.getContent());
            pstmt.setInt(3, boardDTO.getWriterId());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<BoardDTO> selectAll() {
        ArrayList<BoardDTO> list = new ArrayList<>();

        String query = "SELECT * FROM `board` ORDER BY `id` DESC";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                BoardDTO b = new BoardDTO();
                b.setId(resultSet.getInt("id"));
                b.setTitle(resultSet.getString("title"));
                b.setContent(resultSet.getString("content"));
                b.setWriterId(resultSet.getInt("writerId"));
                b.setEntryDate(resultSet.getTimestamp("entry_date"));
                b.setModifyDate(resultSet.getTimestamp("modify_date"));

                list.add(b);
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public BoardDTO selectOne(int id) {
        BoardDTO boardDTO = null;
        String query = "SELECT * FROM `board` WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                boardDTO = new BoardDTO();
                boardDTO.setId(resultSet.getInt("id"));
                boardDTO.setTitle(resultSet.getString("title"));
                boardDTO.setContent(resultSet.getString("content"));
                boardDTO.setWriterId(resultSet.getInt("writerId"));
                boardDTO.setEntryDate(resultSet.getTimestamp("entry_date"));
                boardDTO.setModifyDate(resultSet.getTimestamp("modify_date"));
            }

            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return boardDTO;
    }

    public void update(BoardDTO boardDTO) {
        String query = "UPDATE `board` SET `title` = ?, `content` = ?, `modify_date` = NOW() WHERE `id` = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, boardDTO.getTitle());
            pstmt.setString(2, boardDTO.getContent());
            pstmt.setInt(3, boardDTO.getId());

            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id){
        String query = "DELETE FROM `board` WHERE `id` = ?";

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









