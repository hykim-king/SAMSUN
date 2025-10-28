package bcm_1modul; // 


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BCM {


	static class ECM {
		private String name;
		private String phoneNumber;
		private String relationship;


		public ECM(String name, String phoneNumber, String relationship) {
			this.name = name;
			this.phoneNumber = phoneNumber;
			this.relationship = relationship;
		}


		public String getName() {
			return name;
		}


		public String getPhoneNumber() {
			return phoneNumber;
		}


		public String getRelationship() {
			return relationship;
		}


		@Override
		public String toString() {
			return name + " (" + relationship + ") - " + phoneNumber;
		}
	}


	private static List<ECM> contactList = new ArrayList<>();


	public static void startBCM(Scanner scanner) {
		initDummyData();


		while (true) {
			System.out.println("\n=========================================");
			System.out.println("💼 [BCM 모듈]");
			System.out.println("=========================================");
			System.out.println("1. 비상연락망");
			System.out.println("2. 위기대응 메뉴얼");
			System.out.println("0. 메인 메뉴로 돌아가기");
			System.out.print("\n👉 입력: ");


			String mainChoice = scanner.nextLine().trim();
			System.out.println("-----------------------------------------");


			switch (mainChoice) {
			case "1":
				showEmergencyContactMenu(scanner);
				break;
			case "2":
				showCrisisManualMenu(scanner);
				break;
			case "0":
				System.out.println("...[BCM 모듈]을 종료하고 메인 메뉴로 돌아갑니다.");
				return;
			default:
				System.out.println("❌ 잘못된 입력입니다. 0~2 사이의 숫자를 입력하세요.");
			}
		}
	}


	public static void showEmergencyContactMenu(Scanner scanner) {
		while (true) {
			System.out.println("\n=========================================");
			System.out.println("📞 [비상연락망 관리]");
			System.out.println("=========================================");
			System.out.println("1. 모든 연락처 보기");
			System.out.println("2. 연락처 추가");
			System.out.println("3. 연락처 검색 및 삭제");
			System.out.println("0. 이전으로 돌아가기");
			System.out.print("\n👉 입력: ");


			String input = scanner.nextLine().trim();
			System.out.println("-----------------------------------------");


			switch (input) {
			case "1":
				showAllContacts(scanner); // scanner 넘김
				break;
			case "2":
				addContact(scanner);
				break;
			case "3":
				searchAndRemove(scanner);
				break;
			case "0":
				return;
			default:
				System.out.println("❌ 잘못된 입력입니다. 0~3 사이의 숫자를 입력하세요.");
			}
		}
	}


	public static void showCrisisManualMenu(Scanner scanner) {
		while (true) {
			System.out.println("\n=========================================");
			System.out.println("📗 [위기대응 메뉴얼]");
			System.out.println("=========================================");
			System.out.println("1. 공중위생관리법");
			System.out.println("2. 소방시설법");
			System.out.println("3. 재난 및 안전관리 기본법");
			System.out.println("0. 이전으로 돌아가기");
			System.out.print("\n👉 입력: ");


			String input = scanner.nextLine().trim();
			System.out.println("-----------------------------------------");


			switch (input) {
			case "1":
				showPublicHealthLaw();
				break;
			case "2":
				showFireSafetyLaw();
				break;
			case "3":
				showDisasterLaw();
				break;
			case "0":
				return;
			default:
				System.out.println("❌ 잘못된 입력입니다. 0~3 사이의 숫자를 입력하세요.");
			}
		}
	}


	public static void showAllContacts(Scanner scanner) {
		if (contactList.isEmpty()) {
			System.out.println("⚠️ 등록된 연락처가 없습니다.");
			return;
		} else {


			System.out.println("[전체 비상연락망 목록]");
			System.out.println("------------------------------------------------------");
			System.out.printf("| %-6s | %-8s | %-15s |\n", "이름", "직급", "전화번호");
			System.out.println("------------------------------------------------------");
			for (ECM contact : contactList) {
				System.out.printf("| %-6s | %-8s | %-15s |\n", contact.getName(), contact.getRelationship(),
						contact.getPhoneNumber());
			}
			System.out.println("------------------------------------------------------");
		}


		System.out.println("\n...계속하려면 Enter 키를 누르세요...");
		scanner.nextLine();
	}


	public static void addContact(Scanner scanner) {
		System.out.println("[신규 연락처 추가]");
		System.out.print("👉 이름: ");
		String name = scanner.nextLine();
		System.out.print("👉 전화번호: ");
		String phone = scanner.nextLine();
		System.out.print("👉 관계 (예: 원장, 디자이너, 거래처): ");
		String relationship = scanner.nextLine();
		contactList.add(new ECM(name, phone, relationship));
		System.out.println("✅ 연락처가 추가되었습니다.");
	}


	public static void searchAndRemove(Scanner scanner) {
		System.out.println("[연락처 검색 및 삭제]");
		System.out.print("👉 검색할 이름을 입력하세요: ");
		String name = scanner.nextLine();


		ECM found = null;
		for (ECM contact : contactList) {
			if (contact.getName().equalsIgnoreCase(name)) {
				found = contact;
				break;
			}
		}


		if (found != null) {
			System.out.println("🔎 찾은 연락처: " + found);
			System.out.print("👉 삭제하시겠습니까? (Y/N): ");
			String confirm = scanner.nextLine().trim().toUpperCase();
			if (confirm.equals("Y")) {
				contactList.remove(found);
				System.out.println("🗑️ 연락처가 삭제되었습니다.");
			} else {
				System.out.println("❎ 삭제가 취소되었습니다.");
			}
		} else {
			System.out.println("⚠️ '" + name + "' 님을 찾을 수 없습니다.");
		}
	}


	public static void showPublicHealthLaw() {
		System.out.println("📘 [공중위생관리법 (요약)]");
		System.out.println("① 영업신고: 관할 시·군·구청에 반드시 미용업 영업신고 필요.");
		System.out.println("② 면허증 게시: 미용사 면허증 원본을 가게 내부에 게시.");
		System.out.println("③ 위생관리: 기구 소독(자외선 등) 및 구분 보관.");
		System.out.println("④ 위생교육: 매년 1회 위생교육 필수 이수.");
	}


	public static void showFireSafetyLaw() {
		System.out.println("🔥 [소방시설법 (요약)]");
		System.out.println("① 소화기: 모든 공간에서 20m 이내 접근, 33㎡ 이상 방에 추가 설치.");
		System.out.println("② 피난시설: 출입문/복도/계단 등 피난 동선에 장애물 적치 금지.");
	}


	public static void showDisasterLaw() {
		System.out.println("🌧️ [재난 및 안전관리 기본법 (요약)]");
		System.out.println("① 정부/지자체 명령(영업중단, 대피 등)은 반드시 이행.");
		System.out.println("② 재난 피해 시 신고 후 소상공인 자금/복구비 등 신청 가능.");
	}


	public static void initDummyData() {
		if (contactList.isEmpty()) {
			contactList.add(new ECM("홍길동", "010-1234-5678", "원장"));
			contactList.add(new ECM("김철수", "010-8765-4321", "부원장"));
			contactList.add(new ECM("박영희", "010-5678-1234", "수석실장"));
		}
	}
}
