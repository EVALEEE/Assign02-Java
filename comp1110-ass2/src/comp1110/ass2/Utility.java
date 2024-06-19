package comp1110.ass2;

public class Utility {

    /**
     * Element [x][0] is the side of the board with fire, in the north orientation
     * Element [x][1] is the side of the board without fire, in the north orientation
     */
    public static String[][] SQUARE_BOARDS = {
            // Board 1
            {""" 
            fffgygbyr
            fffgygpby
            fffrrbrgp
            fffbgypbr
            fffpbrpgy
            fffyrygyp
            fffgbbrpb
            fffpggbyg
            fffpypgrr
            """,
                    """
            gbygygbyr
            brpgygpby
            ygbrrbrgp
            bypbgypbr
            gprpbrpgy
            rbgyrygyp
            ybygbbrpb
            ygrpggbyg
            bbypypgrr
            """
            },
            // Board 2
            {""" 
            fffyggybp
            fffpyyrgy
            fffrgbpgb
            fffbrgyrp
            fffppbgrb
            fffbyrpby
            fffbygbrp
            fffprgryg
            fffybyppy
            """,
                    """
            gbbyggybp
            rbgpyyrgy
            ygyrgbpgb
            bprbrgyrp
            gbrppbgrb
            pygbyrpby
            gbybygbrp
            pygprgryg
            gbrybyppy
            """},
            // Board 3
            {""" 
            fffpybygr
            fffrpgbyb
            fffbrbgyr
            fffgpypbg
            fffrbgpry
            fffpyrygb
            fffrggbpg
            fffpbyryb
            fffgybrgy
            """,
                    """
            grgpybygr
            gyprpgbyb
            brbbrbgyr
            gypgpypbg
            pbrrbgpry
            ygppyrygb
            bpyrggbpg
            brypbyryb
            gybgybrgy
            """},
            // Board 4
            {"""
            fffpgyyrb
            fffpbbryp
            fffbgypgb
            fffyybyrg
            fffbgrggy
            fffgpgbgp
            fffbgybpb
            fffpyrgyg
            fffgppbrr
            """,
                    """
            rrbpgyyrb
            ygypbbryp
            bprbgypgb
            rgbyybyrg
            pypbgrggy
            ygygpgbgp
            bprbgybpb
            rybpyrgyg
            bgygppbrr
            """}
    };

    /**
     * Element [x][0] is the side of the board with fire, in the north orientation
     * Element [x][1] is the side of the board without fire, in the north orientation
     */
    public static String[][] RECTANGLE_BOARDS = {
            // Board 1
            {"""
            fffbrgprg
            fffrbpyby
            fffpgybrr
            fffbybgyp
            fffbrgygg
            fffybpbry
            """,
                    """
            yypbrgprg
            bpgrbpyby
            gbypgybrr
            grgbybgyp
            rypbrgygg
            bpgybpbry
            """},
            // Board 2
            {"""
            fffgrybbr
            fffybgryp
            fffbpbygg
            fffyprypy
            fffrygpbb
            fffbpygyr
            """,
                    """
            bbggrybbr
            grpybgryp
            pgrbpbygg
            gpbyprypy
            bygrygpbb
            grybpygyr
            """},
            // Board 3
            {"""
            fffygybgy
            fffgyrpbg
            fffpbyyrp
            fffgbrggr
            fffpgbypb
            fffgrpryb
            """,
                    """
            brpygybgy
            grbgyrpbg
            gyppbyyrp
            ybygbrggr
            gyrpgbypb
            pbbgrpryb
            """},
            // Board 4
            {"""
            fffbgpgpb
            fffyrgbyr
            fffgybpgy
            fffbrrbgb
            fffpygybp
            fffgpbyry
            """,
                    """
            gyybgpgpb
            prbyrgbyr
            yprgybpgy
            ggybrrbgb
            brgpygybp
            bprgpbyry
            """}
    };

    // Red stripe facing north in physical game
    public static String[] DECK_A = {
            "abgbbgybby",
            "bgbbbrrbrr",
            "crrrrgrggy",
            "dggbygbygb",
            "errgrrbgbb",
            "fryrryrryr",
            "grrbrbbbbb",
            "hbbrbyrbyb",
            "irbyrbyrry",
            "jbbbbrrbrr",
            "krrygrygrr",
            "lrrygryrgg",
            "mbbybbrgrr",
            "nrrrrbyrby",
            "obbbbgbygy",
            "prrryrgyrg",
            "qbbbybgybb",
            "rrybrybbyb",
            "sgbbrrbrrr",
            "trgbrgbrgb",
            "ugyrgyrggr",
            "vybrybrybr",
            "wggrgrrrrr",
            "xbbygbygbb",
            "ybgbbgbbgb"
    };

    // Green stripe north in physical game
    public static String[] DECK_B = {
            "agppgpyggy",
            "brrrgbggbg",
            "crbyrbyrby",
            "dgggygrygr",
            "eyggbggbbr",
            "fbbybpybpp",
            "gggggpggpr",
            "hggggggbbb",
            "ibbypbypbb",
            "jbbbbrbrry",
            "kgbgbgbgbg",
            "lbgbggbbbg",
            "mrrgrggggg",
            "nggppppggg",
            "oggbggbpgg",
            "pbbbbbbggg",
            "qbbbybpybp",
            "rbbbbgybgy",
            "sbyrbrrbbr",
            "tgrrgrrggg",
            "ubrbbrbbrb",
            "vpyrpyrpyr",
            "wggygrygry",
            "xygrygrygr",
            "ybpgbpgbpg"
    };

    // Green stripe facing north in physical game
    public static String[] DECK_C = {
            "ayyybyrbyr",
            "bgrggrggrg",
            "cbyybyrryr",
            "dyyyyryprg",
            "epybpybpyb",
            "fyybgybgyy",
            "gppgpggggg",
            "hggggrrgrr",
            "iyygyggppp",
            "jgggygpygp",
            "kypyypyygy",
            "lrrggggygg",
            "mggbgpbgpb",
            "nrrrgpggpg",
            "oyybyrbyrr",
            "pggyygyygg",
            "qybpyppyyp",
            "rbgpbgpbgp",
            "syyyygyygy",
            "trggyggyyp",
            "uppyyyyrry",
            "vgrggryggg",
            "wggggyggyg",
            "xyryyryyry",
            "yggggggyyy"
    };


    // Purple stripe north in physical game
    public static String[] DECK_D = {
            "appbgpbgpp",
            "bppppgpygb",
            "cpbrprrppr",
            "dyyryypbpp",
            "egggyrpyrp",
            "fyypygpygp",
            "grryryyyyy",
            "hyypgypgyy",
            "iggybgybgy",
            "jygpygpygp",
            "kyggygbyyb",
            "lyyybybbyp",
            "mppbpgbpgg",
            "nggpgppppp",
            "ogrpgrpggp",
            "pyyyybybbb",
            "qgyyppyppp",
            "rpbbpbbgbb",
            "sbyrbyrbyr",
            "tpgypgypgy",
            "uppppybpyb",
            "vyyyypbybb",
            "wrpbrpbrpb",
            "xpppbyybyy",
            "ypprppyryy"
    };

    // Number facing north in physical game
    public static final String[] CAT_CARDS = {
            // This card is 1 in the game
            "0rrfrRfrrf",
            // This card is 3 in the game
            "1fffbBbbbb",
            // This card is 4 in the game
            "2fffbBbbbY",
            // This card is 5 in the game
            "3gffgGfggg",
            // This card is 7 in the game
            "4ffyfYyyyy",
            // This card is 9 in the game
            "5fppfPpfpp",
            // This card is 10 in the game
            "6fppfPpfpR"
    };

    public static final String[] RAFT_CARDS = {
            // Card A
            "0wwwwowwww",
            // Card B
            "1gyprowbww",
            // Card C
            "2prpbowbyg",
            // Card D
            "3pbyrorgwg"
    };

    public static final String[] FIRE_TILES = {
            "a000110111221",
            "b000102102021",
            "c000102031121",
            "d000102121314",
            "e000111122021",
            "f000102101120",
            "g000102111213",
            "h0001021121",
            "i011011121321",
            "j001011121322",
            "k0001021020",
            "l000111121322",
            "m00011112",
            "n0001112021",
            "o000102101121",
            "p0001111213",
            "q000102031112",
            "r0001111222",
            "s000110111222",
            "t0001020310",
            "u000102101213",
            "v000102031012",
            "w0010111222",
            "x0110111221",
            "y0001111221",
            "z00101120",
            "A0001020311",
            "B000102112122",
            "C0001021011",
            "D000102031011",
            "E000102121323",
    };

    public final static String[] CHALLENGES = new String[]{
            // difficulty 0
            "LNSNLASAF000300060012001503030903C112033060340009R01215", // First steps
            "LNSNLASAF000300090015030309031203C106033000650012R11215", // Ancient trail
            "LNSNLASAF000300060012001506030903C000093030341203R11215", // Crossed paths
            "LNSNLASAF00030006001203030903C00015100091120350603R21215", // Down the river
            // difficulty 1
            "SNSNSASAF00030015C00006000094030350012R30909", // Early morning
            "SASASASAF0009001203000600C10006300154000050900R10915", // The protector
            "SNSNSASAF000300060012C00303100094090350015R30915", // Wet paws
            "SNSASASAF000600090012C10015306004000350900R10915", // Mind the gap
            // difficulty 2
            "LASALASAF000000030009001503000900C00012312004060050006R20915", // Waiting for friends
            "LNSNLASAF0003000600120903120312061215C00303100094001551212R10915", // In the way
            "LNSALASAF000300090015C00012100061120050900R20915", // The old Teruvian
            "LNSNLASAF0003000600090303C0060311203303064001250015R11215", // Like a rainbow
            "LESALESAF1200120912121215C109003060040300R10615", // Closing in
            "SNSASASAF0003000600090015C109003001240600R20315", // Fussy cats
            "LNSNLASAF0003000600090303090309121215C0001211203309153121240015R00603", // River rafts
            "LASNLASAF00000003000903000909C30006300154001250600R31209", // Me first!
            // difficulty 3
            "SNSNSASAF0003000903030903C00006100153060340012R30906", // Down in the valley
            "SNSNSASAFC1000930003300154000650012R30909", // Narrow spaces
            "LNSNLASAF000300060009001203030903C00912100151060351203R21215", // Tricky waters
            "SNSNSASAF00030009001503030915C10012306034000650903R10615", // All over the place
            "SNSNSASAF00030006000906030915C00015109033001240303R10912", // Stuck in the forest
            "SNSNSASAF000300150303C0000600009300123090340603R30912", // Meet the twins
            "LESNLASAF00090015060009060909C01209103003001251212R20615", // Burning trees
            "LNSNLASAF00030006000903030603C00012200153090351203R11215", // Yanna's children
            // difficulty 4
            "LALAF1500C000032000630000R11506", // Running into fire
            "LNSNLASAF000300060012001503030312060306121203C109033120640009R20315", // Hot sands
            "LASAFC100003000640003R20906", // Clear the way
            "LNSNLASSF00030006001203031203C00009106033001540903R11212", // Running in circles
            "LNSNLSSSF000600121212C0120920003303064031251206R00009", // Everyone is welcome
            "LASNLASAF00000003000903000909C30006300154060050012R31209", // Switch places
            "LASNLESAF0000000603001203C000030031530309309094060950600R31209", // Keeping a balance
            "LNSNLSSSF0003000609091212C000093091250012R31209", // Around the island
            // difficulty 5
            "LNSNLNSAFC1090330003300154000660012R31209", // In the wrong place
            "SASNSNSAF0000001206090903C2000630300R20015", // Drawing straws
            "SNSNSASAF00030009060309030915C10303300124000650015R30912", // Mixed messages
            "LESALASAF001200151200120912121215C00600100093030041203R20615", // Straight dash
            "LNSNLASAF000300060012001503030603061212031206C100093031240903R20315", // It's meow or never
            "LNSNLASAF0003000900150303030903150603090312091215C100122000661203R01212", // Down the waterfall
            "LNSNLASAF00030006030306030606060909030906C000123000940015R31203" // Burning tails
    };
}
