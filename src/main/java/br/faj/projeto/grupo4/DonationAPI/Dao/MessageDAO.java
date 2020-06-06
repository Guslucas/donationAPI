package br.faj.projeto.grupo4.DonationAPI.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

}
