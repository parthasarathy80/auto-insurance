package com.auto.spring.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.auto.spring.jdbc.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.auto.spring.jdbc.common.UtilCommon;
import com.auto.spring.jdbc.model.Quote;

@Repository
public class JdbcQuoteRepository implements QuoteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int save(Quote quote) {

        String INSERT_SQL = "INSERT INTO quotes (name,zip, dob,quote) VALUES(?,?,?,?)";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, quote.getName());
                ps.setString(2, quote.getZip());
                try {
                    Date dobDate = UtilCommon.stringToDate(quote.getDob(), Constants.dateFormatstr);
                    ps.setDate(3, new java.sql.Date(dobDate.getTime()));
                } catch (SQLException | ParseException e) {
                    e.printStackTrace();
                }
                ps.setDouble(4, quote.getQuote());
                return ps;
            }

        }, holder);

        return (int) holder.getKeys().get("ID");

    }

    @Override
    public int update(Quote quote) {
        return jdbcTemplate.update("UPDATE quotes SET name=?, zip=?, dob=? WHERE id=? ",
                new Object[]{quote.getName(), quote.getZip(), quote.getDob(), quote.getId()});
    }

    @Override
    public Quote findById(Long id) {
        try {
            Quote quote = jdbcTemplate.queryForObject("SELECT * FROM quotes WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Quote.class), id);
            return quote;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Quote> findAll() {
        try {
            return jdbcTemplate.query("SELECT * from quotes", BeanPropertyRowMapper.newInstance(Quote.class));
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public List<Quote> findlast24hoursQuotes(String zip, String quoteCondition) {

        StringBuffer query = new StringBuffer("SELECT * from quotes where createdTime >NOW() - INTERVAL 1 DAY");

        if (!zip.isEmpty())
            query.append(" and zip =" + zip);
        if (!quoteCondition.isEmpty())
            query.append(" and quote " + quoteCondition);

        try {
            return jdbcTemplate.query(query.toString(), BeanPropertyRowMapper.newInstance(Quote.class));
            // return jdbcTemplate.query("SELECT * from quotes", BeanPropertyRowMapper.newInstance(Quote.class));
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
}
