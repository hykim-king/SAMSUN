package bcm_1modul; // 

import java.util.Scanner;

public class SalonOwnerPrototype {

    // 1번 -> 2번 메뉴 데이터 전달용
    private static String dailySpecialNote = "";

    // 2. BCM 모듈용 (인력 리스크 제거 -> 동적 처리)
    private static final String DUMMY_BCM_REPORT = """
            [BCM 모듈] (핵심 리스크 점검)
            --------------------------------------------------
            - (경고!) [고객 리스크] 최근 1주 누적 노쇼 8건. 
                         예상 손실액: 400,000원.
            - (확인) [설비] '디지털 펌 기기 1호' 정기 점검일이 15일 남았습니다.
            - (참고) 상세 재고 현황은 '3. 재고 관리' 모듈을 이용하세요.
            --------------------------------------------------
            """;

    // 1. 경영 모듈용 (분석 리포트)
    private static final String DUMMY_ANALYSIS_REPORT = """
            [간단 경영 현황 분석] (가상 데이터)
            --------------------------------------------------
            - 재방문 고객 비율: 68% (목표 70% 근접)
            - 평균 객단가: 52,000원 (지난달 대비 +3,000원)
            - 재료비 비율: 15.2% (적정 수준 유지 중)
            - (분석) 가장 예약률이 저조한 시간은 [수요일 14:00-16:00]입니다.
            
            --------------------------------------------------
            [시각화 분석] (콘솔 데모 - 요일별 매출)
            * (그래프 요약) 금요일 매출이 가장 높으며, 수요일이 가장 저조합니다.
            
            [요일별 매출 현황 그래프 (가상)]
            월요일: ■■■■■ (50만)
            화요일: ■■■■■■■ (70만)
            수요일: ■■■ (30만)
            목요일: ■■■■■■■■ (80만)
            금요일: ■■■■■■■■■■■ (110만)
            토요일: ■■■■■■■■■ (90만)
            일요일: ■■■■■ (50만)
            --------------------------------------------------
            """;

    public static void startPrototype(Scanner scanner) {
        dailySpecialNote = ""; 
        boolean isRunning = true;
        
        while (isRunning) {
            isRunning = runPrototypeCycle(scanner);
            
            if (isRunning) {
                System.out.println("\n...계속하려면 Enter 키를 누르세요...");
                scanner.nextLine(); 
            }
        }
        System.out.println("\n...[미용실 대시보드]를 종료하고 메인 메뉴로 돌아갑니다."); // [통일] 복귀
    }

    private static boolean runPrototypeCycle(Scanner scanner) {
        System.out.println("\n=========================================");
        System.out.println("💼 [미용실 원장님 전용 대시보드]"); // [통일] 헤더
        System.out.println("=========================================");
        System.out.println("무엇을 하시겠습니까?");
        System.out.println("1. 경영 모듈 (매출 입력 및 분석)"); 
        System.out.println("2. BCM 모듈 (핵심 리스크 점검)"); 
        System.out.println("3. 재고 관리 (실행형 모듈)");
        System.out.println("0. 메인 메뉴로 돌아가기"); // [통일] 복귀
        
        System.out.print("\n👉 입력: "); // [통일] 프롬프트
        String choice = scanner.nextLine(); 
        System.out.println("-----------------------------------------");

        switch (choice) {
            case "1":
                System.out.println("[경영 모듈] 1. 오늘의 데이터를 입력합니다.");
                try {
                    System.out.print("👉 1. 총 매출액(원): "); // [통일] 프롬프트
                    scanner.nextLine(); 
                    System.out.print("👉 2. '노쇼(No-show)' 건수: "); // [통일] 프롬프트
                    scanner.nextLine();
                    System.out.print("👉 3. 총 방문 고객 수(명): "); // [통일] 프롬프트
                    scanner.nextLine();
                    System.out.print("👉 4. 신규 방문 고객 수(명): "); // [통일] 프롬프트
                    scanner.nextLine();
                    System.out.print("👉 5. 재료 매입 비용(원): "); // [통일] 프롬프트
                    scanner.nextLine();
                    System.out.print("👉 6. 오늘의 특이사항 (인력/설비 이슈 등, 없으면 Enter): "); // [통일] 프롬프트
                    dailySpecialNote = scanner.nextLine(); 
                    
                    System.out.println("\n✅ 입력 완료. (저장되지 않음)");
                    if (!dailySpecialNote.isEmpty()) {
                        System.out.println("   (특이사항이 BCM 모듈에 전달되었습니다.)");
                    }
                    
                    System.out.println("\n[경영 모듈] 2. 입력 데이터를 기반으로 분석 리포트를 표시합니다.");
                    System.out.println(DUMMY_ANALYSIS_REPORT); 
                    
                } catch (Exception e) {
                    System.out.println("❌ 입력 중 오류 발생: " + e.getMessage()); // [통일] 오류
                }
                break; 

            case "2":
                if (dailySpecialNote != null && !dailySpecialNote.isEmpty()) {
                    System.out.println("[BCM 모듈] (핵심 리스크 점검)");
                    System.out.println("--------------------------------------------------");
                    System.out.println("🚨 (긴급!) [입력된 특이사항] " + dailySpecialNote); // [통일] 아이콘
                    String[] dummyLines = DUMMY_BCM_REPORT.split("\n");
                    for (int i = 2; i < dummyLines.length; i++) {
                         System.out.println(dummyLines[i]);
                    }
                } else {
                    System.out.println(DUMMY_BCM_REPORT);
                }
                break;

            case "3":
                InventoryManager.startInventoryManagement(scanner);
                break;

            case "0":
                return false; 

            default:
                System.out.println("❌ 잘못된 입력입니다. 1, 2, 3, 0 중에서 선택해주세요."); // [통일] 오류
                break;
        }
        return true; 
    }
}