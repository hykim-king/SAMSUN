package bcm_1modul; // 

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


class Product {
	private String name;
	private int count;

	public Product(String name, int count) {
		this.name = name;
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public int getcount() {
		return count;
	}

	public void setcount(int count) {
		this.count = count;
	}

	public String getStockStatus() {
		if (count <= 3)
			return "△ 부족";
		else if (count <= 7)
			return "■ 보통";
		else
			return "○ 여유";
	}
}

class Inventory {
	private ArrayList<Product> inventory;
	private static final String FILE_NAME = "products.txt";

	public Inventory() {
		inventory = new ArrayList<>();
		loadInventoryFromFile();
	}

	public void addProduct(String name, int count) {
		Product exist = findProduct(name);
		if (exist != null) {
			exist.setcount(exist.getcount() + count);
			System.out.println("✅ 수량이 업데이트되었습니다."); // [통일] 아이콘
		} else {
			inventory.add(new Product(name, count));
			System.out.println("✅ 제품이 추가되었습니다."); // [통일] 아이콘
		}
		saveInventoryToFile();
	}

	public Product findProduct(String name) {
		for (Product p : inventory) {
			if (p.getName().equalsIgnoreCase(name)) {
				return p;
			}
		}
		return null;
	}

	public void showInventory(Scanner scanner) {
		System.out.println("\n-----------------------------------------"); // [통일] 구분선
		System.out.println("[현재 재고 현황]");
		System.out.println("-----------------------------------------"); // [통일] 구분선
		if (inventory.isEmpty()) {
			System.out.println("⚠️ 재고가 비어 있습니다.");
		} else {
			int index = 1;
			for (Product p : inventory) {
				System.out.printf("%d. %-12s | 수량: %-3d | 상태: %s\n", index++, p.getName(), p.getcount(),
						p.getStockStatus());
			}
		}
		System.out.println("\n...계속하려면 Enter 키를 누르세요...");
		scanner.nextLine(); // 엔터 대기
	}

	private void loadInventoryFromFile() {
		File file = new File(FILE_NAME);
		if (!file.exists()) {
			String[] initialProducts = { "샴푸", "린스", "트리트먼트", "염색약", "환원제", "중화제", "털이개", "라텍스장갑", "음료", "다과" };
			for (String productName : initialProducts) {
				inventory.add(new Product(productName, 0));
			}
		} else {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split(",");
					if (parts.length == 2) {
						String name = parts[0].trim();
						int count = Integer.parseInt(parts[1].trim());
						inventory.add(new Product(name, count));
					}
				}
			} catch (IOException e) {
				System.err.println("❌ 재고 파일 로딩 오류: " + e.getMessage()); // [통일] 오류
			}
		}
	}

	private void saveInventoryToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
			for (Product p : inventory) {
				writer.write(p.getName() + ", " + p.getcount() + "\n");
			}
		} catch (IOException e) {
			System.err.println("❌ 재고 파일 저장 오류: " + e.getMessage()); // [통일] 오류
		}
	}
}

public class InventoryManager {

	public static void startInventoryManagement(Scanner scanner) {
		Inventory manager = new Inventory();

		String[] productList = { "샴푸", "린스", "트리트먼트", "염색약", "환원제", "중화제", "털이개", "라텍스장갑", "음료", "다과" };

		boolean run = true;

		while (run) {
			System.out.println("\n========================================="); // [통일] 헤더
			System.out.println("📦 [재고 관리 모듈]"); // [통일] 아이콘/헤더
			System.out.println("========================================="); // [통일] 헤더
			System.out.println("1. 재고 확인");
			System.out.println("2. 제품 입고 (등록/수량추가)");
			System.out.println("0. 이전 메뉴로 돌아가기");
			System.out.print("\n👉 입력: "); // [통일] 프롬프트
			String input = scanner.nextLine();
			System.out.println("-----------------------------------------"); // [통일] 구분선

			switch (input) {
			case "1":
				manager.showInventory(scanner);
				break;

			case "2":
				System.out.println("[제품 입고]");
				System.out.println("입고할 제품을 선택하세요!");
				for (int i = 0; i < productList.length; i++) {
					System.out.println((i + 1) + ". " + productList[i]);
				}
				System.out.print("\n👉 번호 입력: "); // [통일] 프롬프트
				String productChoiceStr = scanner.nextLine();

				int productChoice = -1;
				try {
					productChoice = Integer.parseInt(productChoiceStr);
				} catch (NumberFormatException e) {
					System.out.println("❌ 잘못된 입력입니다. 숫자를 입력하세요."); // [통일] 오류
					continue;
				}

				if (productChoice < 1 || productChoice > productList.length) {
					System.out.println("❌ 잘못된 입력입니다. (1~10)"); // [통일] 오류
					continue;
				}

				String selectedName = productList[productChoice - 1];

				System.out.print("👉 입고할 수량: "); // [통일] 프롬프트
				int count;
				try {
					count = Integer.parseInt(scanner.nextLine());
					if (count < 0) {
						System.out.println("❌ 수량은 0 이상이어야 합니다."); // [통일] 오류
						continue;
					}
				} catch (NumberFormatException e) {
					System.out.println("❌ 수량은 숫자로 입력해주세요."); // [통일] 오류
					continue;
				}

				manager.addProduct(selectedName, count);
				break;

			case "0":
				run = false;
				System.out.println("...[재고 관리 모듈]을 종료하고 이전 메뉴로 돌아갑니다."); // [통일] 복귀
				break;

			default:
				System.out.println("❌ 올바른 메뉴 번호를 입력해주세요. (0~2)"); // [통일] 오류
				break;
			}
		}
	}
}
