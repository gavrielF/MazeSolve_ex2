import matplotlib.pyplot as plt
import matplotlib.patches as mpatches

rectangle = [

(-0.002066028, 0.4555509), (-0.00861678, 1.177744), (-0.02600022, 1.944213), (-0.05300392, 2.688168), (-0.0890186, 3.409492), (-0.1334337, 4.108081), (-0.1856375, 4.783845), (-0.2490433, 5.480968), (-0.3198763, 6.188542), (-0.3995382, 6.917536), (-0.4747364, 7.635832), (-0.5534052, 8.387281), (-0.625255, 9.139412), (-0.6971047, 9.891546), (-0.7535875, 10.6227), (-0.7994126, 11.34347), (-0.8368851, 12.03134), (-0.8645422, 12.70855), (-0.8835876, 13.40829), (-0.8900376, 14.11937), (-0.8867622, 14.84159), (-0.8634858, 15.57455), (-0.822992, 16.31789), (-0.7639428, 17.08228), (-0.6989192, 17.83503), (-0.6204656, 18.55298), (-0.5368104, 19.25915), (-0.440359, 19.96369), (-0.3469218, 20.64622), (-0.2488332, 21.33931), (-0.1491876, 22.04341), (-0.05424334, 22.73694), (0.04522211, 23.4635), (0.1477017, 24.21207), (0.2482424, 24.97212), (0.3384332, 25.73346), (0.4159437, 26.47386), (0.4730228, 27.17153), (0.5206046, 27.86991), (0.5523395, 28.56919), (0.5745575, 29.26884), (0.581108, 29.99103), (0.5715843, 30.69096), (0.5483075, 31.42393), (0.5145571, 32.1676), (0.4693825, 32.93294), (0.4137995, 33.69759), (0.3540014, 34.42848), (0.2853215, 35.14743), (0.2155952, 35.84395), (0.1389133, 36.51737), (0.04857113, 37.20031), (-0.04637447, 37.89384), (-0.1540603, 38.60799), (-0.2585063, 39.32262), (-0.3645591, 40.04825), (-0.46704, 40.79682), (-0.5675821, 41.55686), (-0.6505495, 42.28549), (-0.7181726, 42.99338), (-0.7752527, 43.69105), (-0.8228356, 44.38943), (-0.8634272, 45.07712), (-0.8888192, 45.77666), (-0.9051952, 46.4987), (1.011101E7, 1.011101E7), (-1.307923, 44.24551), (-1.226106, 44.65974), (-1.104897, 45.33788), (-0.986132, 46.039), (1.011101E7, 1.011101E7), (-1.66295, 44.37109), (-1.497119, 44.75939), (-1.239423, 45.38626), (-0.9750288, 46.0464), (-0.7106348, 46.70653), (1.011101E7, 1.011101E7), (-1.839922, 45.08286), (-1.59259, 45.42506), (-1.211932, 45.97236), (-0.8022445, 46.56714), (-0.3771757, 47.1783), (1.011101E7, 1.011101E7), (-1.977443, 45.70119), (-1.655053, 45.99074), (-1.136396, 46.46084), (-0.5930413, 46.95332), (-0.03919214, 47.45076), (1.011101E7, 1.011101E7), (-1.512174, 46.67096), (-1.083036, 46.88099), (-0.4443202, 47.19359), (0.2029294, 47.51402), (0.8487192, 47.83737), (1.470317, 48.15927), (2.110135, 48.49429), (1.011101E7, 1.011101E7), (0.4849599, 48.07746), (0.8741578, 48.16979), (1.519923, 48.33542), (2.207108, 48.51833), (2.925629, 48.71307), (3.621837, 48.90516), (4.348326, 49.11271), (5.084535, 49.32666), (5.798456, 49.53765), (6.511412, 49.75187), (7.211785, 49.96926), (7.877437, 50.18584), (8.544066, 50.39939), (1.011101E7, 1.011101E7), (6.635063, 50.30963), (7.057203, 50.31798), (7.734603, 50.3406), (8.422578, 50.37608), (9.144002, 50.41), (9.876184, 50.45109), (10.59708, 50.49483), (11.31777, 50.54183), (12.08258, 50.5952), (12.83579, 50.65463), (13.5998, 50.71839), (14.34138, 50.78367), (15.0384, 50.84822), (15.74586, 50.92021), (16.44123, 51.00055), (17.11341, 51.08748), (17.8398, 51.18814), (18.55377, 51.297), (19.2662, 51.41555), (20.01035, 51.54634), (20.76357, 51.68931), (21.50456, 51.83694), (22.23399, 51.9857), (22.92944, 52.13411), (23.61197, 52.28951), (24.29233, 52.45418), (24.95883, 52.62835), (25.64518, 52.81438), (26.36099, 53.01888), (27.05266, 53.22671), (27.75103, 53.45045), (28.46845, 53.68747), (1.011101E7, 1.011101E7), (26.29307, 53.58517), (26.72628, 53.5957), (27.41478, 53.6187), (28.1809, 53.64777), (1.011101E7, 1.011101E7), (26.40104, 54.0384), (26.83141, 53.92744), (27.52158, 53.75615), (28.25489, 53.57416), (28.99814, 53.38613), (1.011101E7, 1.011101E7), (26.9324, 54.4711), (27.35447, 54.22438), (27.96839, 53.86552), (28.62412, 53.49017), (29.26383, 53.13164), (1.011101E7, 1.011101E7), (27.85286, 54.48565), (28.15863, 54.1786), (28.64915, 53.6949), (29.16446, 53.20486), (29.70043, 52.70436), (1.011101E7, 1.011101E7), (28.73572, 54.224), (28.95961, 53.85299), (29.31436, 53.28855), (29.70695, 52.68235), (30.12339, 52.05192), (30.53312, 51.44373), (30.93044, 50.85397), (1.011101E7, 1.011101E7), (30.28307, 52.86338), (30.4028, 52.44691), (30.60477, 51.79992), (30.82936, 51.11351), (31.05778, 50.39331), (31.27962, 49.68269), (31.49824, 48.97107), (31.71347, 48.28166), (31.9251, 47.61442), (32.14578, 46.95011), (32.36889, 46.29835), (32.60588, 45.61612), (32.85972, 44.91629), (33.12305, 44.21998), (33.38158, 43.54561), (1.011101E7, 1.011101E7), (33.12984, 45.87651), (33.16659, 45.44473), (33.23325, 44.77024), (33.31655, 44.03047), (33.40108, 43.27966), (33.48341, 42.51743), (33.5667, 41.77766), (33.64875, 41.04893), (33.73472, 40.34304), (33.8242, 39.65998), (33.91988, 38.97777), (34.02652, 38.28594), (34.14993, 37.5518), (34.27934, 36.84126), (34.41727, 36.12102), (34.57282, 35.38165), (34.72838, 34.64228), (34.89063, 33.90435), (35.04892, 33.19969), (35.21733, 32.50881), (35.39235, 31.83104), (35.57658, 31.15572), (35.76998, 30.48296), (35.97894, 29.79163), (36.20401, 29.08203), (36.43835, 28.38715), (36.67845, 27.706), (36.92314, 26.99116), (37.15463, 26.28363), (37.37647, 25.57301), (37.58857, 24.88263), (37.79122, 24.201), (37.98767, 23.52913), (38.18103, 22.84482), (38.44561, 21.89198), (38.64182, 21.18538), (38.8316, 20.47703), (39.02666, 19.73559), (39.21225, 19.00319), (39.39184, 18.28073), (39.56875, 17.56905), (39.74069, 16.8905), (39.91572, 16.21273), (40.094, 15.54731), (40.27804, 14.86043), (1.011101E7, 1.011101E7), (40.2421, 16.53782), (40.24342, 16.13783), (40.26043, 15.48249), (40.28312, 14.73839), (1.011101E7, 1.011101E7), (40.68706, 16.7312), (40.60357, 16.32866), (40.48003, 15.67353), (40.3546, 14.97357), (40.22917, 14.27361), (1.011101E7, 1.011101E7), (41.01147, 16.12633), (40.84604, 15.71382), (40.60809, 15.07919), (40.34358, 14.38332), (40.08153, 13.67466), (1.011101E7, 1.011101E7), (41.2911, 15.49899), (41.04455, 15.11592), (40.69075, 14.53781), (40.28759, 13.88571), (39.89027, 13.24306), (1.011101E7, 1.011101E7), (41.43747, 14.79141), (41.12285, 14.46195), (40.66082, 13.95097), (40.14405, 13.38464), (1.011101E7, 1.011101E7), (41.48722, 14.29431), (41.07196, 14.01582), (40.48865, 13.60908), (39.86889, 13.17693), (1.011101E7, 1.011101E7), (41.35109, 13.74933), (40.87599, 13.56089), (40.23132, 13.28811), (39.54705, 12.99491), (38.87564, 12.69998), (1.011101E7, 1.011101E7), (40.97229, 13.02842), (40.52322, 12.9518), (39.84755, 12.8175), (39.13983, 12.6735), (38.44431, 12.52539), (37.73862, 12.37177), (1.011101E7, 1.011101E7), (39.90455, 12.3154), (39.49376, 12.33169), (38.83843, 12.34874), (38.12742, 12.36079), (37.40525, 12.36976), (36.67197, 12.37886), (35.94977, 12.38455), (35.19422, 12.38365), (34.42756, 12.38621), (1.011101E7, 1.011101E7), (36.64916, 11.79083), (36.2131, 11.92266), (35.53241, 12.12845), (34.79755, 12.34699), (34.08497, 12.56242), (33.3927, 12.76829), (32.7111, 12.97099), (32.03924, 13.16748), (31.36477, 13.35481), (30.6779, 13.53889), (29.97781, 13.71634), (29.30854, 13.87953), (28.60461, 14.04106), (27.87827, 14.20427), (27.1179, 14.36789), (26.37784, 14.52013), (25.65824, 14.66138), (24.94772, 14.79083), (24.26889, 14.90815), (23.58805, 15.01314), (22.89484, 15.1104), (22.18976, 15.20281), (21.48468, 15.29523), (1.011101E7, 1.011101E7), (23.57357, 14.5051), (23.17184, 14.66756), (22.5401, 14.91309), (21.87498, 15.16467), (21.20986, 15.41625), (20.50317, 15.68356), (19.78732, 15.95804), (19.08185, 16.22855), (18.37395, 16.49265), (17.70547, 16.73516), (17.03482, 16.97159), (16.38307, 17.19474), (15.77337, 17.40349), (15.15822, 17.59557), (14.54852, 17.80432), (13.83496, 18.01652), (13.15335, 18.21922), (12.49671, 18.42753), (11.86502, 18.64061), (11.19875, 18.85531), (10.57039, 19.04217), (9.949456, 19.21463), (9.368522, 19.36468), (8.773516, 19.50406), (8.142289, 19.63392), (7.541074, 19.74345), (6.980642, 19.82727), (6.451224, 19.89178), (5.886389, 19.93731), (5.187952, 19.98406), (4.334574, 20.04507), (3.44741, 20.10041), (2.594318, 20.16528), (1.863096, 20.22089), (1.131874, 20.2765), (0.4110524, 20.32146), (-0.2883983, 20.34919), (-1.010376, 20.36798), (-1.732432, 20.38349), (-2.443488, 20.39232), (-3.198986, 20.40169), (1.011101E7, 1.011101E7), (-1.079654, 19.85428), (-1.485435, 19.97095), (-2.139308, 20.14937), (-2.836913, 20.33632), (-3.58818, 20.53766), (1.011101E7, 1.011101E7), (-1.869839, 19.60412), (-2.254454, 19.82683), (-2.869838, 20.18318), (-3.512369, 20.55914), (1.011101E7, 1.011101E7), (-2.254164, 19.38315), (-2.583314, 19.71396), (-3.095029, 20.22362), (-3.620102, 20.75135), (-4.131817, 21.26101), (1.011101E7, 1.011101E7), (-3.155055, 19.65726), (-3.368601, 20.03433), (-3.721815, 20.63868), (-4.08619, 21.24934), (-4.459067, 21.86786), (1.011101E7, 1.011101E7), (-3.852369, 19.85741), (-3.963308, 20.2648), (-4.150113, 20.90476), (-4.361899, 21.59523), (-4.570553, 22.28666), (-4.788855, 22.99837), (1.011101E7, 1.011101E7), (-4.637372, 20.99298), (-4.661533, 21.41451), (-4.70952, 22.09058), (-4.770449, 22.81023), (-4.838942, 23.54036), (-4.911833, 24.28122), (-4.985812, 25.03315), (-5.059791, 25.78507), (-5.136042, 26.5256), (1.011101E7, 1.011101E7), (-5.507376, 24.41355), (-5.41065, 24.84735), (-5.256668, 25.55296), (-5.100318, 26.26943), (-4.955013, 26.96554), (-4.809708, 27.66165), (-4.675067, 28.33726), (-4.541368, 29.02437), (-4.413083, 29.73511), (-4.298129, 30.4256), (-4.182758, 31.13855), (-4.068831, 31.88546), (-3.956669, 32.64388), (-3.852915, 33.39228), (-3.758805, 34.11955), (-3.675875, 34.837), (-3.602983, 35.52202), (-3.538391, 36.21903), (-3.487279, 36.90602), (-3.443497, 37.62691), (-3.409527, 38.34834), (-3.388971, 39.05915), (-3.377577, 39.80351), (-3.36932, 40.57013)

]


# rectangle = [(0,0),(0,1),(1,1),(1,0)]
# hexagon = [(0,0),(0,1),(1,2),(2,1),(2,0),(1,-1)]
# l_shape = [(0,0),(0,3),(1,3),(1,1),(3,1),(3,0)]
# concave = [(0,0),(0,3),(1,3),(1,1),(2,1),(2,3),(3,3),(3,0)]

for points in [rectangle]:


    x, y = zip(*points)

    x = [item for item in x if item != 10111010]
    y = [item for item in y if item != 10111010]

    plt.scatter(x, y)
    plt.show()
