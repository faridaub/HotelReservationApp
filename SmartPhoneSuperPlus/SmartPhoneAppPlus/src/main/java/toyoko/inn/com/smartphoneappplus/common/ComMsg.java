package toyoko.inn.com.smartphoneappplus.common;

/**
 * Created by Farid on 2014/11/21.
 */
public class ComMsg {

    public static String FLD_RESERVATION_NO ="予約番号";
    public static String FLD_CHECKIN ="チェエクイン";
    public static String FLD_CHECKOUT ="チェックアウト";
    public static String FLD_ROOM1 ="お部屋";
    public static String FLD_ROOM2 ="部屋";
    public static String FLD_USING_PEOPLE ="ご利用人数";
    public static String FLD_ACOMOINFO ="ご宿泊者情報";
    public static String FLD_FAMILY_NAME1 = "性";
    public static String FLD_FAMILY_NAME2 = "性（半角アルファベット）";
    public static String FLD_FIRST_NAME1 = "名";
    public static String FLD_FIRST_NAME2 = "名（半角アルファベット）";
    public static String FLD_MEMBERSHIP_FLAG1 = "会員/一般";
    public static String FLD_MEMBERSHIP_FLAG2 = "東横INNｸﾗﾌﾞｶｰﾄﾞ会員";
    public static String FLD_MEMBERSHIP_NUM = "会員番号";
    public static String FLD_COUNTRY = "国籍";
    public static String FLD_GENDER = "性別";
    public static String FLD_PHONE_NUM = "電話番号";
    public static String FLD_CHECKIN_TIME = "ﾁｪｯｸｲﾝ予定時刻";
    public static String FLD_OPTION_SELECTION ="選択したオプション";
    public static String FLD_ECO_PLAN1 = "連泊ecoプラン";
    public static String FLD_ECO_PLAN2 = "ECO プラン";
    public static String FLD_ECO_PLAN3 = "プラン対象日";
    public static String FLD_BUSINESSPACK = "ビジネスパック";
    public static String FLD_VOD1 = "VOD";
    public static String FLD_VOD2 = "VOD（+500円)";
    public static String FLD_BABY_FLAG = "お子様添い寝";
    public static String FLD_PAYMENT_AMOUNT ="お支払い金額";
    public static String FLD_ACC_DATE ="宿泊日";
    public static String FLD_ACCOMODATION ="宿泊先";
    public static String FLD_BREAKDWON ="内訳";
    public static String FLD_HAKUBI_KINRYOU ="拍日（会員料金）";




    public static String LV_TELEPHONENUMBER ="当日連続可能な電話番号を入力してください。";
    public static String LV_EACH_ROOM_NAME ="名 (部屋あたり)";
    public static String LV_NAME ="性名は半角アルファベットで入力してください。";
    public static String LV_AMOUNT_NOTICE = "チェックイン時には必ずフロントにカードをご提示ください。提示がない場合は・・・（内容後日送付します）";
    //public static String LV_SEX ="性別選択してください。";
    public static String LV_ECOPLAN ="お部屋の清掃、ナイトウェア、シーツ類の交換、アメニティグッズの補充を省かせていただきその分宿泊料金が2泊目以降、1泊につき300円お安くなります。";
    public static String LV_VODPLAN ="500円（税込）で200以上のコンテンツ見放題！";
    public static String LV_BUSINESSPACK ="宿泊にVISAギフトカードをつけることができます。領収書の金額はセット料金でお出しします";
    public static String LV_BABYBAD = "お子様（小学生以下）が1名まで無料で添い寝可能です。"
            + "添い寝されるお子様がいる場合は「あり」にしてください。"
            + "※添い寝のお子様のアメニティグッズ、タオル、枕等は付きません。"
            + "ご利用の場合は別途1,000円かかります。";



    public static String SW_TERGETDATE ="対象日";
    public static String SW_MEMBER ="会員";
    public static String SW_GENERAL ="一般";
    public static String SW_GENDER ="性別";
    public static String SW_OPTION ="オプション";
    public static String SW_YES_E ="YES";
    public static String SW_NO_E ="YES";
    public static String SW_YES ="はい";
    public static String SW_NO ="いいえ";
    public static String SW_FMALE ="女性";
    public static String SW_MALE ="男性";
    public static String SW_TOTALIZATION1 ="集計";
    public static String SW_TOTALIZATION2 ="小計";
    public static String SW_TAX_INC ="税込";
    public static String SW_ROOM = "お部屋";
    public static String SW_AGREEMENT ="規約";
    public static String SW_COUNTRY ="国籍";
    public static String SW_NIGHTDATE ="拍日";
    public static String SW_UPDATE ="変更する";
    public static String SW_KEN ="軒";



    public static String MSG_PROCESSING ="実施中....";
    public static String MSG_TOYOKO_INN_GROUPCARD ="東横INNクラブカード会員";
    public static String MSG_TOYOKO_INN_GENERAL ="東横INN一般";
    public static String MSG_PASSWORD_HIDE ="ｾｷｭﾘﾃｨの観点から表示しません";
    public static String MSG_LOGIN_BUTTON ="ログインして予約する";
    public static String MSG_APPLIED_Y ="指定あり";
    public static String MSG_APPLIED_N ="指定なし";


    public static String MSG_BUSINESSPLAN_1A = "ﾋﾞｼﾞﾈｽﾊﾟｯｸ100";
    public static String MSG_BUSINESSPLAN_2A = "ﾋﾞｼﾞﾈｽﾊﾟｯｸ200";
    public static String MSG_BUSINESSPLAN_3A = "ﾋﾞｼﾞﾈｽﾊﾟｯｸ300";

    public static String MSG_BUSINESSPLAN_1B = "（1000円分のVISAｷﾞﾌﾄｶｰﾄ）";
    public static String MSG_BUSINESSPLAN_2B = "（2000円分のVISAｷﾞﾌﾄｶｰﾄ）";
    public static String MSG_BUSINESSPLAN_3B = "（3000円分のVISAｷﾞﾌﾄｶｰﾄ）";


    public static String NUM_BUSINESSPLAN_1 = "100";
    public static String NUM_BUSINESSPLAN_2 = "200";
    public static String NUM_BUSINESSPLAN_3 = "300";
    public static String NUM_BUSINESSPLAN_0 = "00";

    public static String ERR_ECO_SElECT_THREEDAY ="連拍ＥＣＯプランは、３日連続は選択できません。";
    public static String ERR_NO_HOTEL_FOUND ="該当するホテルがありません。条件を変更して再度お試しください。";
    public static String ERR_SYSTEM_ERROR =" ネットワーク接続が切れました。";
    public static String ERR_RESERV_CANCEL ="予約をキャンセルします。よろしいですか？";
    public static String ERR_ACC_RESERV ="現在予約中のホテルはありません。";
    public static String ERR_ACC_HISTORY ="宿泊履歴がありません。";
    public static String ERR_FAVORITE_HOTEL ="お気に入りのホテルがありません。";
    public static String ERR_BROWSING_HISTORY ="閲覧履歴がありません。";
    public static String ERR_EMPTY_FIELD ="が未入力です。";
    public static String ERR_CHECK ="を確認ください。";
    public static String ERR_MAIL_CHECK ="メールアドレスを入力してください。";
    public static String ERR_MAIL_MATCH ="1度目に入力したID（メールアドレス）と確認のために入力したものが一致していません。";
    public static String ERR_PASSWORD_LENGTH ="パスワードは6文字以上で入力してください。";
    public static String ERR_PASSWORD_ALPHBATE_NUMBER ="パスワードが一致してません。ご確認ください。";
    public static String ERR_PASSWORD_MATCH ="1度目に入力したパスワードと確認のために入力したパスワードが一致していません。";
    public static String ERR_EMPTY_FILD ="未入力の必須項目があります。ご確認ください。";
    public static String ERR_PHONENO_FILD ="電話番号が短すぎます。正しい番号を入力してください。";
    public static String ERR_CHECK_DOB ="生年月日を確認してください。";
    public static String ERR_LOGIN_EXISTS ="メールアドレスが別のアカウントで使われています。メールアドレスをご確認の上、もう一度入力しなおしてください。";
    public static String ERR_EMPTY_FILD_EMAIL ="メールアドレスが未入力です。";
    public static String ERR_EMPTY_FILD_FAMILY =" 姓（半角アルファベット）が未入力です。";
    public static String ERR_EMPTY_FILD_FIRST =" 名（半角アルファベット）が未入力です。";
    public static String ERR_EMPTY_FILD_DOB ="  生年月日（数字８）が入力してください。";
    public static String ERR_LOGIN_ERROR ="ログインに失敗しました。ID・パスワードをご確認の上、再度お試しください。";

    public static String ERR_TOP_1 ="「行き先」と「人数・室数」をお選びください。";
    public static String ERR_TOP_2 ="「人数・室数」をお選びください。";
    public static String ERR_TOP_3 ="目的地を入力して下さい。";
    public static String ERR_TOP_4 ="GPRSを設定してください。";
    public static String ERR_TOP_5 ="「行き先」をお選びください。";
    public static String ERR_URL ="URLは正しくありません。";
    public static String ERR_CONNECTION ="インタネット接続失費しました";
    public static String ERR_INCORRECT_EMAIL ="メールアドレースは正しくありません。もう一度ご確認ください。";
    public static String ERR_LOGIN_ID_EMPTY ="メールアドレスが未入力です。";
    public static String ERR_PASSWORD_EMPTY ="パスワードが未入力です。";
    public static String ERR_EMPTYCHECK ="未入力の必須項目があります。ご確認ください。";
    public static String ERR_AUTH_ERROR ="お客様情報を確認できませんでした。会員番号・名前を確認の上、再度お試しください。";
    public static String ERR_ERROR_OCCUR ="エラーが発生しました。しばらく待ってから再度お試しください。";
    public static String ERR_G59P59MSG ="電話番号を入力してください。";
    public static String ERR_GPRS_CONNECTION ="GPRS接続できませんでした。GPRS　設定してください。";
    public static String ERR_AUTH_FIELD_EMPTY ="認証キー（半角英数字）が未入力です。";
    //public static String ERR_KEYWORD ="目的地キーワードを入力してください。";
    //public static String ERR_SETTING ="基本設定のパラメータチェックエラー";
    public static String ERR_LOGIN_CHK ="ログインが必要です。";
    //public static String ERR_NULL_VALUE ="Null Value Exception";
    public static String ERR_DEL_BOOKMARK ="お気に入りから削除しますか";
   

    //Condition Message
    public static String COD_Y ="Y";
    public static String COD_EMPTY ="";
    public static String COD_N ="N";
    public static String COD_M ="M";
    public static String COD_F ="F";
    public static String COD_NEXT ="next";
    public static String COD_BACK ="back";
    public static String COD_NORMAL ="normal";
    public static String COD_DATA ="DATA";


    public static String TTL_ADDRESS ="住所";
    public static String TTL_G10P15_ACCESS ="アクセス";
    public static String TTL_G10P15_PARK ="駐車場";
    public static String TTL_G10P15_BUS_PARK ="バス駐車場";
    public static String TTL_G10P15_PICKUP ="送迎";
    public static String TTL_G10P15_BUS_RENTAL ="東横INNレンタカーサービス";
    public static String TTL_G10P15_CHECKIN ="チェックイン";
    public static String TTL_G10P15_CHECKOUT ="チェックアウト";
    public static String TTL_G10P15_BREAKFAST="朝食時間（無料）";
    public static String TTL_G10P15_EQUIPMENT ="館内設備・サービス";
    public static String TTL_G10P15_BARRIER_FEE ="バリアフリー対応";
    public static String TTL_G10P15_IOS ="ISO9001：2008認証取得";
    public static String TTL_G10P15_PHONE_NUM ="電話番号";
    public static String TTL_G10P15_CANCEL_POLICY ="CANCEL_POLICY_COMMSG";


    public static String SIN_SPACE_1 =" ";
    public static String SIN_SPACE_2 ="  ";
    public static String SIN_START_BRECKET="(";
    public static String SIN_START_3D_BRECKET_TAX = "【お部屋";
    public static String SIN_START_BRECKET_TAX="(税込";
    public static String SIN_START_BRECKET_TARGET="(対象日";
    public static String SIN_CLOSE_BRECKET =")";
    public static String SIN_CLOSE_3D_BRECKET_TAX = "】";

    public static String TAG_G10P15_ADDRESS ="G10P15_ADDRESS";
    public static String TAG_G10P15_ACCESS ="G10P15_ACCESS";
    public static String TAG_G10P15_PARK ="G10P15_PARK";
    public static String TAG_G10P15_BUS_PARK ="G10P15_BUS_PARK";
    public static String TAG_G10P15_PICKUP ="G10P15_PICKUP";
    public static String TAG_G10P15_BUS_RENTAL ="G10P15_BUS_RENTAL";
    public static String TAG_G10P15_CHECKIN ="G10P15_CHECKIN";
    public static String TAG_G10P15_CHECKOUT ="G10P15_CHECKOUT";
    public static String TAG_G10P15_BREAKFAST ="G10P15_BREAKFAST";
    public static String TAG_G10P15_EQUIPMENT ="G10P15_EQUIPMENT";
    public static String TAG_G10P15_BARRIER_FEE ="G10P15_BARRIER_FEE";
    public static String TAG_G10P15_IOS ="G10P15_IOS";
    public static String TAG_G10P15_PHONE_NUM ="G10P15_PHONE_NUM";

    public static String TAG_G10P1511 ="G10P15-11";
    public static String TAG_G13P17EQP ="G13P17-EQP";
    public static String TAG_G13P17CP ="G13P17-CP";








    public static String getErrorCode(String errorCode){
        StringBuilder sb = new StringBuilder();
        if (errorCode != null) {
            sb.append("[" + errorCode + "]");
        }
        sb.append("確認");
        return  sb.toString();
    }





}
