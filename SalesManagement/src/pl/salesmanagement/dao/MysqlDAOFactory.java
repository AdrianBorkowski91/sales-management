package pl.salesmanagement.dao;

public class MysqlDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	@Override
	public AccountDAO getAccountDAO() {
		return new AccountDAOImpl();
	}

	@Override
	public ClientDAO getClientDAO() {
		return new ClientDAOImpl();
	}

	@Override
	public MeetingDAO getMeetingDAO() {
		return new MeetingDAOImpl();
	}

	@Override
	public HistoryOfMeetingDAO getHistoryOfMeetingDAO() {
		return new HistoryOfMeetingDAOImpl();
	}

 

}