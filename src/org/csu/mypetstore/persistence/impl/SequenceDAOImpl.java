package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.SequenceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Enzo Cotter on 2019/10/10.
 */
public class SequenceDAOImpl implements SequenceDAO {
    private static final String GET_SEQUENCE = "SELECT " +
            "        name, nextid\n" +
            "        FROM SEQUENCE\n" +
            "        WHERE NAME = ?";

    private static final String UPDATE_SEQUENCE = "UPDATE " +
            "        SEQUENCE\n" +
            "        SET NEXTID = ?\n" +
            "        WHERE NAME = ?";

    @Override
    public Sequence getSequence(Sequence sequence) {
        Sequence sequenceRtn = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SEQUENCE);
            preparedStatement.setString(1, sequence.getName());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sequenceRtn = new Sequence();
                sequenceRtn.setName(resultSet.getString(1));
                sequenceRtn.setNextId(resultSet.getInt(2));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sequenceRtn;
    }

    @Override
    public boolean updateSequence(Sequence sequence) {
        Boolean flag = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SEQUENCE);

            preparedStatement.setInt(1, sequence.getNextId());
            preparedStatement.setString(2, sequence.getName());
            if (preparedStatement.executeUpdate() == 1) {
                flag = true;
            }

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
