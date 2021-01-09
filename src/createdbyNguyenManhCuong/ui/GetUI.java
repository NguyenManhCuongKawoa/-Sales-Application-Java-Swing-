package createdbyNguyenManhCuong.ui;

public class GetUI {
	public static LoginUI loginUI;
	public static HelpUI helpUI;
	public static CreateNewAccountUI createNewAccountUI;
	public static AccountAdminUI accountAdminUI;
	public static AccountUserUI accountUserUI;
	public static QuanLySanPhamUI quanLySanPhamUI;
	public static SanPhamUI sanPhamUI;
	public static FeedbackProductUI feedbackProductUI;
	public static GioHangUI gioHangUI;
	public static AccountUI accountUI;

	
	public static AccountUI getAccountUI() {
		return accountUI;
	}

	public static void setAccountUI(AccountUI accountUI) {
		GetUI.accountUI = accountUI;
	}

	public static GioHangUI getGioHangUI() {
		return gioHangUI;
	}

	public static void setGioHangUI(GioHangUI gioHangUI) {
		GetUI.gioHangUI = gioHangUI;
	}

	public static FeedbackProductUI getFeedbackProductUI() {
		return feedbackProductUI;
	}

	public static void setFeedbackProductUI(FeedbackProductUI feedbackProductUI) {
		GetUI.feedbackProductUI = feedbackProductUI;
	}

	public static void setLoginUI(LoginUI loginUI) {
		GetUI.loginUI = loginUI;
	}
	
	public static void setHelpUI(HelpUI helpUI) {
		GetUI.helpUI = helpUI;
	}

	public static void setCreateNewAccountUI(CreateNewAccountUI createNewAccountUI) {
		GetUI.createNewAccountUI = createNewAccountUI;
	}

	public static void setAccountAdminUI(AccountAdminUI accountAdminUI) {
		GetUI.accountAdminUI = accountAdminUI;
	}

	public static void setAccountUserUI(AccountUserUI accountUserUI) {
		GetUI.accountUserUI = accountUserUI;
	}

	public static void setQuanLySanPhamUI(QuanLySanPhamUI quanLySanPhamUI) {
		GetUI.quanLySanPhamUI = quanLySanPhamUI;
	}

	public static SanPhamUI getSanPhamUI() {
		return sanPhamUI;
	}

	public static void setSanPhamUI(SanPhamUI sanPhamUI) {
		GetUI.sanPhamUI = sanPhamUI;
	}
    
}
