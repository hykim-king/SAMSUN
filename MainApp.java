package bcm_1modul; //

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            boolean appRunning = true;
            System.out.println("--- 소상공인 BCM + 경영 분석 앱 ---");

            while (appRunning) {
                System.out.println("\n=========================================");
                System.out.println("== 사장님, 반갑습니다! ==");
                System.out.println("== 원하시는 메뉴를 선택해주세요. ==");
                System.out.println("=========================================");
                System.out.println("1. [미용실] 통합 대시보드");
                System.out.println("2. [BCM] 위기/재난 관리 (단독 실행)");
                System.out.println("3. [재고] 재고 관리 (단독 실행)");
                System.out.println("0. 전체 프로그램 종료");

                System.out.print("\n👉 입력: "); // [통일] 프롬프트
                String choice = scanner.nextLine();
                System.out.println("-----------------------------------------");

                switch (choice) {
                case "1":
                    SalonOwnerPrototype.startPrototype(scanner);
                    break;
                case "2":
                    BCM.startBCM(scanner);
                    break;
                case "3":
                    InventoryManager.startInventoryManagement(scanner);
                    break;
                case "0":
                    appRunning = false;
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("❌ 잘못된 입력입니다. 0-3 사이의 숫자를 입력하세요."); // [통일] 오류
                    break;
                }

                if (appRunning) {
                    System.out.println("\n...메인 메뉴(상위 목차)로 돌아왔습니다.");
                    System.out.println("...계속하려면 Enter 키를 누르세요...");
                    scanner.nextLine();
                }
            }

        } catch (Exception e) {
            System.err.println("❌ 치명적인 오류가 발생하여 프로그램을 종료합니다: " + e.getMessage());
        }
    }
}