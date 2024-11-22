package cn.mentalhealth.dao.impl;//@Repository
//public class ChatHistoryDaoImpl implements ChatHistoryDao {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    // ...
//
//    @Override
//    public List<ChatHistory> findByHNameAndTimeBetween(String HName, LocalDateTime startTime, LocalDateTime endTime) {
//        String sql = "SELECT * FROM chat_history WHERE HName = ? AND Htime BETWEEN ? AND ?";
//        return jdbcTemplate.query(sql, new Object[]{HName, startTime, endTime}, new ChatHistoryRowMapper());
//    }
//
//    private static class ChatHistoryRowMapper implements RowMapper<ChatHistory> {
//        @Override
//        public ChatHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
//            ChatHistory chatHistory = new ChatHistory();
//            chatHistory.setHid(rs.getLong("Hid"));
//            // ...
//            return chatHistory;
//        }
//    }
//}