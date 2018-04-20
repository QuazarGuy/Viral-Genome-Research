import java.util.ArrayList;
import java.util.List;

public class Comparison {

	public static final int MAX_DISTANCE=0;
	public static void main(String[] args) {
		String[]genes=new String[]{
				"MEGSNGFSSSLAGLSSSRSSLRLLTHFLSLPTLPVNIYLNARRH\n" + 
						"                     SGWYRSPPTLPVNIYLNEQFDNLCLAALRYPGHKLYPSVHTLFPDVSPLKIPHSVPAF\n" + 
						"                     AHLVQRQGLRRQGNSITNIYGNGNDVTTDVGANGMSLPIAVGDMPTASTSEAPLGSNK\n" + 
						"                     GGSSTSPKSTSNGNVVRGSRYSKWWEPAAARALDRALDHAVDATDAVAGAASKGIKAG\n" + 
						"                     AAKLSNKLSGSQTTALLALPGNIAGGAPSATVNANNTSISSQALLPSVNPYPSTPAVS\n" + 
						"                     LPNPDAPTQVGPAADRQWLVDTLSWSETIAPLTVFSGPKALTPGVYPPTIEPNTGVYP\n" + 
						"                     LPAALCVSHPESVFSTAYNAHAYFNCGFDVTVVVNASQFHGGSLIVLAMAEGLGDITP\n" + 
						"                     ADSSTWFNFPHTIINLANSNAATLKLPYIGVTPNTSTEGLHNYWTILFAPLTPLAVPT\n" + 
						"                     GSPTTVKVSLFVSPIDSAFYGLRFPVPFPAPQHWKTRAVPGAGTYGSVVAGQEIPLVG\n" + 
						"                     YAPAAPPRDYLPGRVHNWLEYAARHSWERNLTWTSADEVGDQLVSYPIQPEALANTQT\n" + 
						"                     NTAFVLSLFSQWRGSLQISLIFTGPAQCYGRLLLAYTPPSANPPTTIDEANNGTYDVW\n" + 
						"                     DVNGDSTYTFTIPFCSQAYWKTVDIGTSSGLVSNNGYFTVFVMNPLVTPGPSPPSATV\n" + 
						"                     AAFLHVADDFDVRLPQCPALGFQSGADGAEVQPAPTSDLSDGNPTTDPAPRDNFDYPH\n" + 
						"                     HPVDPSTDLAFYFSQYRWFGLNESLTPLDATGGLFYHISLNPINFQQSSLLSVLGAFT\n" + 
						"                     YVYANLSLNINVSAPSQPCTFYVFYAPPGASVPSVQTLAELSFFTHTATPLNLAAPTN\n" + 
						"                     ITVSIPYSSPQSVLCTSFGGFGLQNGGDAGNLHSNTWGTLILYVDLPQSDSVSVSAYI\n" + 
						"                     SFRDFEAYVPRQTPGVGPVPTSTSIVRVARPTPKPRTARRQGGTLADLILSPESRCFI\n" + 
						"                     VAHTTAPFYSILLVNPDEEYAISMFSHGDESILQYSSRSGTRLTPTAPAFFLCAAASV\n" + 
						"                     DTVLPYSISQSHLWLTDLTGIPLRAVPPLTLFLSAGAALCAGAQTLIAVAQGGSTPET\n" + 
						"                     PPTPNRALLRRQGLGDLPDAAKGLSAALESVARVAGDANIATSSQAIATSINSLSNSI\n" + 
						"                     DGATSFMQNFFSGLAPRNPTSPLQHLFAKLIKWVTKIIGSLIIICNNPTPSALIGVSL\n" + 
						"                     MLCGDLAEDITEFFSNLGNPLAAVFYRCARALGLSPTPQSAAQAAGGRQGVRDYNDIM\n" + 
						"                     SALRNTDWFFEKIMTHIKNLLEWLGVLVKDDPRTKLNGQHEKILELYTDSVTASSTPP\n" + 
						"                     SELSADAIRSNLDLAKQLLTLSHAANSVTHIQLCTRAITNYSTALSAISLVGTPGTRP\n" + 
						"                     EPLVVYLYGPPGTGKSLLASLLASTLAQALSGDPNNYYSPSSPDCKFYDGYSGQPVHY\n" + 
						"                     IDDIGQDPDGADWADFVNIVSSAPFIVPMADVNDKGRFYTSRVVIVTSNFPGPNPRSA\n" + 
						"                     RCVAALERRLHIRLNVTARDGVAFSAAAALQPSNPPSATRYCKFANPLTQFSMFNLAV\n" + 
						"                     DYKSVVLPNTPLTCFDELVDFVLSSLRDRASVNSLLSGMVRTDVTRQGGNADAPAPSA\n" + 
						"                     APLPSVIPSVPSQDPFTRAVNENRPVSFLSKIWSWRAPIFAASSFLSLIAATLTIVRC\n" + 
						"                     LRDLRSTQGAYSGTPVPKPRKKDLPKQPVYSGPVRRQGFDPAVMKIMGNVDSFVTLSG\n" + 
						"                     TKPIWTMSCLWIGGRNLIAPSHAFVSDEYEITHIRVGSRTLDVSRVTRVDDGELSLLS\n" + 
						"                     VPDGPEHKSLIRYIRSASPKSGILASKFSDTPVFVSFWNGKSHSTPLPGVVDEKDSFT\n" + 
						"                     YRCSSFQGLCGSPMIATDPGGLGILGIHVAGVAGYNGFSARLTPERVQAFLSHLATPQ\n" + 
						"                     SVLYFHPPMGPPAHVSRRSRLHPIPPAFGAFPITKEPAALSRKDPRLPEGTDLDAITL\n" + 
						"                     AKHDKGDIATPWPCMEEAADWYFSQLPDNLPVLSQEDAIRGLDHMDAIDLSQSPGYPW\n" + 
						"                     TTQGRSRRSLFDEDGNPLPELQEAIDSVWDGGSYIYQSFLKDELRPTAKARAGKTRIV\n" + 
						"                     EAAPIQAIVVGRRLLGSLINHLQGNPLQHGSAVGCNPDIHWTQIFHSLTSFSNVWSID\n" + 
						"                     YSCFDATIPSVLLSAIASRIAARSDQPGRVLDYLSYTTTSYHVYDSLWYTMIGGNPSG\n" + 
						"                     CVGTSILNTIANNIAVISAMMYCNKFDPRDPPVLYCYGDDLIWGSNQDFHPRELQAFY\n" + 
						"                     QKFTNFVVTPADKASDFPDSSSIFDITFLKRYFVPDDIHPHLIHPVMDEQTLTNSIMW\n" + 
						"                     LRGGEFEEVLRSLETLAFHSGPKNYSAWCEKIKAKIRENGCDATFTPYSVLQRGWVST\n" + 
						"                     CMTGPYPLTG",
						"MASKPFYPIEFNPRVELQVLRSAHLRVGGREQMFETINDLNDHV\n" + 
								"                     RGVVAGLWCKHLHRSLATAPTFTEEGLLDSFLSKPPVDINPDTTFRELFGVDPHEQFP\n" + 
								"                     LSIHDLAKLQGELVDAARNPGHVLRRHYSTDSLTALINKITKYVPVHATLQEMQARRA\n" + 
								"                     FERERAELFKELPHADLDVSRQQKSYFYAMWRQVVKKGKEFFIPLVKCTSWRKKFTEP\n" + 
								"                     AEIVRQVLVHFCEGMRSQFSTNVNYINLSLIAKLRPTVLTMILQQHKNTYRGWLATVT\n" + 
								"                     ALVEVYSNLFQDMRDTAVSAVSAITLVFETIKDFIVNVIDLVKSTFQSQGPTPCGWAA\n" + 
								"                     IVAGAVLILMKLSGCSNTTSYWHRLLKVCGGVTTIAAAARAVVWVRDIIAEADGKARL\n" + 
								"                     KKYMARTAALLELAASRDVTGTDELKXLLDCFTQLIEEGTELIQEFGTSPLAGLTRSY\n" + 
								"                     VSELESTANSIRSTILLDTPRKAPVAIILTGPPGIGKTRLAQHLAAGFGKVSNFSVTL\n" + 
								"                     DHHDSYTGNEVAIWDEFDVDTQGKFVETMIGIVNTAPYPLNCDRVENKGKVFTSDYII\n" + 
								"                     CTSNYPTSVLPDNPRAGAFYRRVTTIDVSSPTIEDWKKKNPGKKPPPDLYKNDFTHLR\n" + 
								"                     LSIRPFLGYNPEGDTLDGVRVKPVLTSVDGLSRLMETKFKEQGNEHRNLWITCPRDLV\n" + 
								"                     APATSGLKAYMAANRALAQVFQEPSSQDIGETCTSRVYVSCNNPPPTYSGRVVKITAI\n" + 
								"                     NPWDASLANSMLSMFETTSHIPASTQREIMYRVWDPLVHLQTREPNTQMLPYINRVVP\n" + 
								"                     VSSAFDFIRGLRHHLGLCSVKGMWRAYQGWNSSSSILEFLSKHMADVTFPHNPECTVF\n" + 
								"                     RAPDGDVIFYTFGSYACFVSPARVPFVGDPPKTVHSNITRNMTWAETLRLLAETITES\n" + 
								"                     LVHFGPFLLMMHNVSYLATRSGREEEAKGKTKHGRGAKHARRGGVSLSDDEYDEWRDL\n" + 
								"                     VRDWRQDMTVGEFVELRERYALGMDSEDVQRYRAWLELRAMRMGAGAYQHATIIGRGG\n" + 
								"                     IQDTIVRTQPMRAPRAPRNQGYDEEAPTPIVTFTSGGDHIGYGCHMGNGVVVTVTHVA\n" + 
								"                     SASDQVEGQEFTIRKTEGETTWVNTNLGHLPHYQIGDGAPVYYSARLHPVTTLAEGTY\n" + 
								"                     ETPNITVQGYHLRILNGYPTKRGDCGTPYFDSCRRLVGLHAATSTNGETKLAQRVTKT\n" + 
								"                     SKVENAFAWKGLAVVRGPDCGGMPTGTRYHRSPAWPNPVEGETHAPAPFGSGDERYKF\n" + 
								"                     SQVEMLVNGLKPYSEPTPGVPPALLQRAATHTRTYLETIIGTHRSPNLSFNEACSLLE\n" + 
								"                     KSTSCGPFVAGQKGDYWDEDKQCYTGVLAEHLAKAWDAANRGVAPQNAYKLALKDELR\n" + 
								"                     PIEKNAQGKRRLLWGCDAGATLVATAAFKGVATRLQAVAPMTPVGVGINMDSYQVEVL\n" + 
								"                     NESLKGGVLYCLDYSKWDSTQHPAVTAASLGILERLSEATPITTSAVELLSSPARGHL\n" + 
								"                     NDIVFITKSGLPSGMPFTSVINSLNHMTYFAAAVLKAYEQHGAPYTGNVFQVETVHTY\n" + 
								"                     GDDCLYSVCPATASIFQTVLANLTSFGLKPTAADKSETIAPTHTPVFLKRTLTCTPRG\n" + 
								"                     VRGLLDITSIKRQFLWIKANRTVDINSPPAYDRDARSIQLENALAYASQHGHTVFEEV\n" + 
								"                     AELARHTAKAEGLVLTNVNYDQALATYESWFIGGTGLVQGSPSEETTKLVFEMEGVPR\n" + 
								"                     PEGPKANSNENVPLASPQDTIGPNAALLLPTQIETPNGAAQRVEMAAATGAVSNNVPM\n" + 
								"                     CVRECFASVTTLPWTTRQASNTFLGAIHLGPRINPYTAHLSAMFAGWGGSFQIRVTLS\n" + 
								"                     GSGLYAGRAVVAVLPPGVNPANVQNPGVFPHAFIDARTVDPILINLPDIRAVDYHRVD\n" + 
								"                     GDEQTATVGLWVAQPLINPFQTGSISTCWLTFETRPGPDFDFCLLKAPEQEMDNGISP\n" + 
								"                     ANLLPRRLGRSRGNRLGGRVVGLVVVAVAEQVNHHFAANSTTLGWSTLPIEPIAGAIS\n" + 
								"                     WYQNTTPGISTRGLLSAEGKGIIFPNIVNHWTDVALSSKTSGRTTVPTDQANLNQCPG\n" + 
								"                     ASGPVVMFQNNGDVNETSANNCVLTAASHDFVNLSSNFDAAGMWVWLPWTTTKPDATI\n" + 
								"                     NRNVYITPTWINGDPSRPIHGKCTNMVGTNFQFGGTGTNNIMLWQEQHFTSFPGAAEV\n" + 
								"                     YCSQLESTAEMFQNNVVNIPANQMAVFNVETAGNTFQIAIMPNGYCVTNAAVGTHQLL\n" + 
								"                     DYETSFRFVGLFPQSTSLQGPNGNAGRAVRFLE",
								"MAYGEPYYSSKPDKDFNFGSTMARRQMTPTMVTKLPKFVRNSPQ\n" + 
								"                     AYDWIVRGLIFPTIGKTYFQRVVVITGGLEDGTYGSFAFDGKEWVGIYPIEHLNLMSS\n" + 
								"                     LKLIHKANALQERLRLSQEEKATLALDVQFLQHENVRLKEMIPKPEPRKIQMKWIIMG\n" + 
								"                     AVLTFLSLIPGGYAHSQTNNTIFTDMIAACKYSTETLTENLDLRIKLALANITISDKL\n" + 
								"                     DAVRQILNFAFVPRAHWLRTVFYYIHYYEMWNIFMFVLAIGTVMRSARPGTDLVTLAT\n" + 
								"                     SHLSGFRMAVLPTIPFHTTMTLWVMNTLMVCYYFDNLLAITLAILAPILGIIFLCFME\n" + 
								"                     DSNYVSQIRGLIATAVLIAGGHACLTLTGTTTSLFVVILTCRFIRMATVFIGTRFEIR\n" + 
								"                     DANGKVVATVPTRIKNVAFDFFQKLKQSGVRVGVNEFVVIKPGALCVIDTPEGKGTGF\n" + 
								"                     FSGNDIVTAAHVVGNNTFVNVCYEGLMYEAKVRYMPEKDIAFLTCPGDLHPTARLKLS\n" + 
								"                     KNPDYSCVTVMAYVNEDLVVSTAAAMVHGNTLSYAVRTQDGMSGAPVCDKYGRVLAVH\n" + 
								"                     QTNTGYTGGAVIIDPADFHPVKAPSQVELLKEEIERLKAQLNSATENATTVVTQQPSA\n" + 
								"                     ALEQKSVSDSDVVDLVRTAMEREMKVLRDEINGILAPFLQKKKGKTKHGRGRVRRNLR\n" + 
								"                     KGVKLLTEEEYRELLEKGLDRETFLDLIDRIIGERSGYPDYDDEDYYDEDDDGWGMVG\n" + 
								"                     DDVEFDYTEVINFDQAKPIPAPRTTKQKICPEPEVESQPLDLSQKKEKQSEYEQQVVK\n" + 
								"                     STKPQQLEHEQQVVKPIKPQKSEPQPYSQTYGKAPIWESYDFDWDEDDAKFILPAPHR\n" + 
								"                     LTKADEIVLGSKIVKLRTIIETAIKTQNYSALPEAVFELDKAAYEAGLEGFLQRVKSK\n" + 
								"                     NKAPKNLQRAPEDQGAQNYHSLDAWKLLLEPPRERRCVPANFPLLGHLPINRPIFDDK\n" + 
								"                     KPRDDLLGLLPEPTWHAFEEYGPTTWGPQAFIKSFDKFFYAEPIDFFSEYPQLCAFAD\n" + 
								"                     WATYREFRYLEDTRVIHITATEKNTDSTPAYPKMNYFDTEENYLEAHGWAPYIREFTR\n" + 
								"                     VYKGDKPEVLWYLFLKKEIIKEEKIRNSDIRQIVCADPIYTRIGACLEAHQNALMKQH\n" + 
								"                     TDTSVGQCGWSPMEGGFKKTMQRLVNKGNKHFIEFDWTRYDGTIPPALFKHIKEIRWN\n" + 
								"                     FINKDQREKYRHVHEWYVNNLLNRHVLLPSGEVTLQTRGNPSGQFSTTMDNNMVNFWL\n" + 
								"                     QAFEFAYFNGPDRDLWKTYDTVVYGDDRLSTTPSVPDDYEERVITMYRDIFGMWVKPG\n" + 
								"                     KVICRDSIVGLSFCGFTVNENLEPVPTSPEKLMASLLKPYKILPDLESLHGKLLCYQL\n" + 
								"                     LAAFMAEDHPFKVYVEHCLSRTAKQLRDSGLPARLTEEQLHRIWRGGPKKCDGMASKSNKQVTVEVSNNGRSRSKSRARSQSRGRDKSVKITVNSRN\n" + 
								"                     RARRQPGRDKRQSSQRVRNIVNKQLRKQGVTGPKPAICQRATATLGTVGSNTSGTTEI\n" + 
								"                     EACILLNPVLVKDATGSTQFGPVQALGAQYSMWKLKYLNVKLTSMVGASAVNGTVSGV\n" + 
								"                     SLNPTTTPTSTSWSGLGARKHLDVTVGKNATFKLKPSDLGGPRDGWWLTNTNDNASDT\n" + 
								"                     LGPSIEIHTLGRTMSSYKNEQFTGGLFLVELASEWCFTGYAANPNLVNLVKSTDNQVS\n" + 
								"                     VTFEGSAGSPLIMNVPEGSHFARTVLARSTTPTTLARAGERTTSDTVWQVLNTAVSAA\n" + 
								"                     ELVTPPPFNWLVKGGWWFVKLIAGRTRTGSRSFYVYPSYQDALSNKPALCTGSTPGGM\n" + 
								"                     RTRNPVTTTLQFTQMNQPSLGHGEAPAAFGRSIPAPGEEFKVVLTFGAPMSPNANNKQ\n" + 
								"                     TWVNKPLDAPSGHYNVKIAKDVDHYLTMQGFTSIASVDWYTIDFQPSEAPAPIQGLQV\n" + 
								"                     LVNSSKKADVYAIKQFVTAQTNNKHQVTSLFLVKVTTGFQVNNYLSYFYRASATGDAT\n" + 
								"                     TNLLVRGDTYTAGISFTQGGWYLLTNTSIVDGAMPPGWVWNNVELKTNTAYHMDKGLV\n" + 
								"                     HLIMPLPESTQMCYEMLTSIPRSRASGHGYESDNTEYLDAPDSADQFKEDIETDTDIE\n" + 
								"                     STEDEDEADRFDIIDTSDEEDENETDRVTLLSTLVNQGMTMTRATRIARRAFPTLSDR\n" + 
								"                     IKRGVYMDLLVSGASPGNAWSHACEEARKAAGEINPCTSGSRGHAE",
								"MAATRVSRSVLAAVAHSAAHRTYHTVLDCYDRLYLNTNPHLSYP\n" + 
								"                     LPKNSSFPCPFCQYDEQNEVLSPESLCGEGAEPCWKCSQDKPRRKYNTTPPEDWLYDS\n" + 
								"                     DVQSWFYPETYYSDLQQKFFDKLALLSLPGAYQAKTPEERALAGALTQLLNFPSTPPL\n" + 
								"                     TLPTTNLQRQGNSVTNIYGNGNNVNTDVGANGWAPTVSTGLGDGPVSCTPPTPSPGDT\n" + 
								"                     EVPPPRKPTTFPAPPTKSGSRFSKWWEPAAARASESATDSAIEGIDAAGKAASKAITR\n" + 
								"                     KLDRPAAPSSTANPQPSLIALNPSATQSGNASILTGSTAPSLLAYPTATPVPLPNPDE\n" + 
								"                     PSQPGPSGDRTWLLDTVTWSQEFTRGWNIAGSNGMQWTGLESLIFPVSTDTNWTSTSS\n" + 
								"                     PTAYPLPFSFVRAYPDSSWAAMYNTHSMWNCGWRVQVTVNGSQFHAGALILYMVPEAT\n" + 
								"                     THAIQTARDNAGFVFPYVILNLYESNTATIEVPYISPTPNTSSGLHAPWTFYLQVLSP\n" + 
								"                     LNPPPSLPTSLSCSIYVTPVDSSFHGLRYLAPQHWKTRAVPGAGTFGSAVAGQELPLC\n" + 
								"                     GVRAYYPPNAYIPAQVRDWLEFAHRPGLMATVPWTMADEPAERLGIFPVSPSAIAGTG\n" + 
								"                     APISYVISLFSQWRGELAAHLLFTGSAQHYGRLVVCYTPAAPQPPSTMQEAMRGTYTV\n" + 
								"                     WDVNAASTLEFTIPFISNSYWKTVDVNNPDALLSTTGYVSIWVQNPLVGPHTAPASAL\n" + 
								"                     VQAFISAGESFNVRLMQNPALTSQTLTEDLDAPQDTGNIENGAADNSPQPRTTFDYTG\n" + 
								"                     NPLPPDTKLENFFSFYRLLPMGGSGAPSLSFPADEGTIIPLDPINWLKGADVSGIAAM\n" + 
								"                     LSCFTYIAADLRITLRFSNPNDNPATMLVAFAPPGATIPLKPTRQMLSNFYMAEVPVS\n" + 
								"                     AATSTMVSFSIPYTSPLSAIPTSYFGWEDWSGTNFGQLSSGSWGNLMLIPSLSVDSAI\n" + 
								"                     PFDFQLSCWVAFGNFKAWVPRPPPPLPPLPTPAANAERTVAVIKQGAASATPDVDPDD\n" + 
								"                     RVYIVRAQRPTYVHWAIRKVAPDGSAKQISLSRSGIQALVALEPPEGEPYLEILPSHW\n" + 
								"                     TLAELQLGNKWEYSATNNCTHFVSSITGESLPNTGFSLALGIGALTAIAASAAVAVKA\n" + 
								"                     LPGIRRQGLLTLSADTETNQTLNKITGSVNQAAQVVSQFDLSGPANSVSLAASDIREA\n" + 
								"                     AHKVASSLNGFTDVIADIKDSLFTRVSDAVESGVATFLTWLVKLFGYLLVLFGSPTPM\n" + 
								"                     SISGLLVIICADLAPHAREFFTASGNVLSSLYYWIASKLGLSVTPQECERATLEPQGL\n" + 
								"                     KDFNDGALAMRNVEWIGETAWKWAHRLLDWIPGKAKTDPQAKLADVHDEIMLHYSDSI\n" + 
								"                     LALGSEKLPIDHITKSISRCRELVSIAQEAKSGPHSSFLNQAVKNYTLAISQHRKCQT\n" + 
								"                     GPRPEPVVVYLYGPPGTGKSLLASLLAQTLSQRLAGTPDDVYSPSSASCEYFDGYTGQ\n" + 
								"                     TVHFIDDIGQDPEGRDWANFPNLVSSAPFIVPMASLEEKGTHYTSKVIVVTSNFHEPN\n" + 
								"                     ERAARSMGALRRRVHLRINVTSNGVPFDPTNALNPIPGTQSKYFTAQTPLTLFQSNTV\n" + 
								"                     RLDRDSIWTPTFTNMDELVDAIVTRLDRSTGVSNSLASLIRRQGNRVIDAEPREIPLE\n" + 
								"                     YADDLLEAMAHHRPVPCSLGLSQAIANNTPIQQISETFWKYRKPIFTCTTFLAVLGFL\n" + 
								"                     CSVIPLARSLWKSKQDTPQEPQAAYSAISHQKPKPKSQKPVPTRHIQRQGISPAVPGI\n" + 
								"                     SNNVVHVESGNGLNKNVMSGFYIFSRFLLVPTHLREPHHTTLTVGADTYDWATLQTQE\n" + 
								"                     FGEITIVHTPTSRQYKDMRRFIGAHPHPTGLLVSQFKAAPLYVRISDNRILDLDFPGV\n" + 
								"                     VVCKQAYGYRAATFEGLCGSPLVTDDPSGVKILGLHVAGVAGTSGFSAPIHPILGQIT\n" + 
								"                     QFATTQQSLIVPTAEVRPGVNVNRMSRLHPSPAYGAFPVKKQPAPLKRNDKRLQEGVD\n" + 
								"                     LDTQLFLKHGKGDVTGPWPGLEAAADLYFSTFPTSLPVLTQEQAIHGTPNMEGLDMGQ\n" + 
								"                     AAGYPWNTLGRSRRSLFDEVEPGVFVPKPELQAEINQTLEDPDYVYSTFLKDELRPTA\n" + 
								"                     KVEQGLTRIVEAAPIHAIVAGRMLLGGLIDYMQGRPGEHGSAVGCNPDVHWTSFFYAF\n" + 
								"                     SEFSQVYDLDYKCFDATLPSAVFTLVADHLTRITGDPRVGRYIHSIRHSHHIYGNRMY\n" + 
								"                     DMIGGNPSGCVATSILNTIINNICVLSALIQHPDFSPSRFHILAYGDDVIYATEPPIH\n" + 
								"                     PSFLREFYQKHTPLVVTPANKGQDFPPTSTIYEVTFLKRWFVPDDVRPIYIHPVMDPD\n" + 
								"                     TYEQSVMWLRDGDFQDVVTSLCHLAFHSGPKTYAAWCMKVREQCLKSGFAPNFLPYSY\n" + 
								"                     LQLRWLNLLAA"
								/*,
								"1 massilisds tftpclthkl srrirrhpva whprensrls yitameelda cyineqhrlf\n" + 
								"       61 plprpshwyn clycqhpsed gdtlmslace ysddcphhss iihpskpvqk dpediledse\n" + 
								"      121 wpdnilhpep psfttdaeqn wldclasasl pgptqvrpls kffaenpppa slagkinlsd\n" + 
								"      181 fsdlslttpc qivrqgnstt niygsnnnvt tdvgangwtp tvntglgdgp vsssaddlpg\n" + 
								"      241 raggsstask dtsksskvkv hnfnhwwepa aakalsrgvd kaldgiegag klassaiksk\n" + 
								"      301 lsgarpgpsp nlialnpsat qsgnamittg stapvvcayp ptpsvplpnp dapsqpgpsg\n" + 
								"      361 drtwlldtle wttedttfwc lagsngmqns aletlqfpvs ssgnwgvqnn vpptayplpf\n" + 
								"      421 sfvhaypncp wtamhdthsm wncgwriqvv vngsqfhagc lalvmvpeys ygtfnqnrqn\n" + 
								"      481 aiftypyall nlyqgntasl evpyigptpn tstcvrgpwt fylvvltplt vptgsptsls\n" + 
								"      541 vsiyvtplnt sfhglrqvqt qhwkirslpg agafgnvvag qeipiyames frppvdylpa\n" + 
								"      601 kvtdwlefah rpglfesytw tmaetagekv aeaviepvll aatgtpisfv tnlfaqwrge\n" + 
								"      661 lqlsllftgs aqhfgrlaic ftpaagtppt slqdalrgty tvwdinssst ldftipfisq\n" + 
								"      721 sywkvtnman qnsllatlgt vsiwvmnplt gpssappsav iqafvsages ftlrgiqapg\n" + 
								"      781 fqlqaaddan aqppvsnies gsattvpepr ttfsytdnpt ppdtnlqrff siyrpifvng\n" + 
								"      841 enysvgftae agytfplnpv dwvanagpgd tlplllscft yftadprval tfsnpapyaa\n" + 
								"      901 citiyfappg sspqtgdtst asmgcfysvq tsvpptsett ipisipyasp lsaiplsffg\n" + 
								"      961 fsdfagghdv vnttfgtlyi rvtfqgdvpa ptyitmtaqi afgnfcgyvp rappplgtlp\n" + 
								"     1021 tpvaavvrpk grsrivrqcq ttdvpaptlc pdvrvyivkv qrmtythwal ravhldrteq\n" + 
								"     1081 islsrlglna yvayedpqgv vyqevepchw mvavamvgtr wdysatnnct hfvsnitgve\n" + 
								"     1141 lpntgfslll glgalallsg caavqaskgr vvrqgllsle appeviqaan rvansietta\n" + 
								"     1201 tvvreadlaq stlnmvhaas rvadsiesta savreadlar stlnismaas dirmaatqvs\n" + 
								"     1261 sslngftdml ssfsktftqg askmlsdgis tfltwvakvf gyllvlfgsp tpmsiagllv\n" + 
								"     1321 iicadlapqa seyfhscssv lgslyywiat klglsvtpee ahaatvehqg vrdyndavna\n" + 
								"     1381 vkntewlmdt cwrwaervlt wirgkaktdp qtvladahde ilrhysesia alssertpvs\n" + 
								"     1441 aitdaitrcr eltkvaadak saphssflsq alrnyqlaln sarmaqsgpr pepvviylyg\n" + 
								"     1501 ppgtgkslla sllarvlaqk lsgnpddvys pssasceyfd gytgqvvhfi ddlgqdpegr\n" + 
								"     1561 dwanfpnlis sapfivpman leakgthyts qvvvvtsnfa gpneraarsi galrrrmhlr\n" + 
								"     1621 invdrldgkp fdpvealkpl nqpskyltsq telslfksfk ltvavdslwq psftdfdslv\n" + 
								"     1681 daivgrldrs tgvsdllasl vkrqgltiea eptelsyqda lemladsrpi sttlsferav\n" + 
								"     1741 aqnaplsvvn qlwnyrkpif acttfltvig fvltiiavar tlwkakedap dktqgaysgl\n" + 
								"     1801 prlkrqekvr pshpppppps qsrsvvrqsl spalpkiadn vypittnspt qgrnascgff\n" + 
								"     1861 lfsrfflapt hiipddtdqi sigpdtfdwa tlqhrrlgke ltiihfptir qhrdlrrfig\n" + 
								"     1921 yhpyptghli stlsgppvyl rfsknrmvtl dlpgvveept aygykaptfq glcgaplitd\n" + 
								"     1981 dpagvkllgl hvagvtgcsg fsvpistylp eieqfaidqq siiipgpniv pgvnvnrksk\n" + 
								"     2041 lgrspafgaf pvkkqpavlt qkddrleegi rlddqlflkh nkgdmdqpwp gleaaadlyf\n" + 
								"     2101 skfptvihtl tmeeaingtp nlegidmnqa agypwntmgr srrslfvqqn giwlplpele\n" + 
								"     2161 aeinktledp yyfystflkd elrpiskvtl gltrvveaap ihaiiagrml lgglieymqa\n" + 
								"     2221 npgkhgsavg cnpdlhwtkf ffkfchypqv fdldykcfda tlpscafriv ekhlerligd\n" + 
								"     2281 ervvryieti rhsrhvfgse tyemiggnps gcvgtsiint iinnicvlsa liqhpdfspe\n" + 
								"     2341 sfrilaygdd viygcdppih psfikefydr htplvvtpan ktdtfpenst iydvtflkrw\n" + 
								"     2401 fvpddirpfy ihpvmdpdty eqsvmwlrdg dfqdlvtslc ylafhsgpkt ydrwctrvrd\n" + 
								"     2461 qvmkttgfpp tflpysylqt rwlnllaa"*/
				
		};
		
		for(int i=0;i<genes.length;i++) {
			genes[i]=genes[i].toUpperCase().replaceAll("\n", "").replaceAll("[ 1-9]","");
		}
		for(int i=0;i<genes.length;i++) {
			for(int j=i+1;j<genes.length;j++) {
				System.out.printf("%d,%d,%s\n",i,j,getSequences(genes[i],genes[j]).toString());
			}
		}	//VVYLYGPPGTGKSLLASLLA
			//APQHWKTRAVPGAGT
		
		
		
		
	}
	
	
	static List<String> getSequences(String a, String b){
		return getSequences(a,b,6);
	}
	
	static List<String> getSequences(String g1, String g2,int max){
		ArrayList<String> found=new ArrayList<String>();
		for(int i=0;i<g1.length()-1;i++) {
			for(int j=i;j<g2.length()-1;j++) {
				
				if(g1.charAt(i)!=g2.charAt(j)) {
					continue;
				}
				int dist=MAX_DISTANCE;
				int count=relation(g1,g2,i,j,dist);
				if(i+count<g1.length()&&j+count<g2.length()&&count>=max) {
					int g1m=g1.length()<i+count+dist?g1.length()-1:i+count+dist;
					found.add(g1.substring(i, g1m));
					
					/*
					if(count>max)max=count;
					System.out.println("###MATCH### "+count);
					int g1m=g1.length()<i+count+dist?g1.length()-1:i+count+dist;
					int g2m=g2.length()<j+count+dist?g2.length()-1:j+count+dist;
					System.out.println(g1.substring(i, g1m));
					System.out.println(g2.substring(j, g2m));
					*/
				}
			}
		}
		for(int i=found.size()-1;i>0;i--) {
			if(found.get(i-1).substring(1).equals(found.get(i))) {
				found.remove(i);
			}
		}
		return found;
		
		
		
	}
	
	
	
	public static int relation(String g1, String g2, int i,int j,int maxDistance) {
		int count=0;
		
		while(i+count<g1.length()&&j+count<g2.length()&&g1.charAt(i+count)==g2.charAt(j+count)) {
			count++;
		}
		if(i+count>=g1.length()||j+count>=g2.length()||maxDistance<=0) {
			return count;
		}
		i+=count;
		j+=count;
		int l=relation(g1,g2,i+1,j,maxDistance-1);
		int r=relation(g1,g2,i,j+1,maxDistance-1);
		int c=relation(g1,g2,i+1,j+1,maxDistance-1);
		
		//System.out.printf("%d\t%d\t%d\t\n",l,r,c);
		return count+Math.max(Math.max(l,r),c);
		
	}

}
