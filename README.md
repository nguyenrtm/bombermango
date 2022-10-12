# Game Bomberman
## Các thành viên
1. Nguyễn Bình Nguyên
2. Đào Thị Kim Thịnh
## Một số tính năng cơ bản
- Nút *M* có thể được dùng để bật nhạc khi chơi.
- Ấn vào *New game* trong menu để bắt đầu trò chơi.
- Trò chơi sẽ có 2 level, khi vượt qua hết 2 level này thì người chơi sẽ thắng cuộc.
- Mỗi level sẽ có 300 giây để chơi, khi hết thời gian này thì bạn sẽ thua.
## Các đối tượng trong trò chơi
### Người chơi
*Player* là đối tượng người chơi, được điều khiển qua các phím:
- *W* để đi lên.
- *A* để sang trái.
- *S* để đi xuống.
- *D* để sang phải.
Ngoài ra, *Player* có chức năng đặt *Bomb* khi nhấn phím *F*.
### Bom
*Bomb* là đối tượng bom. Khi *Bomb* được đặt bởi *Player*, sau một khoảng 3 giây sẽ nổ, phá hủy các đối tượng *Enemy*, *Brick*, cũng như cả *Player* nếu những đối tượng này nằm trong một vùng bán kính đã được xác định so với *Bomb*.
### Địch
1. *Balloom* Đây là đối tượng địch đơn giản nhất, di chuyển ngẫu nhiên, biết chuyển hướng khi va chạm các đối tượng khác, như *Brick*, *Wall* và chuyển hướng khi va chạm với *Bomb*.
2. *Oneal* Đây là đối tượng địch thông minh, khi phát hiện ra *Player* trong một bán kính nhất địch sẽ biết đi theo đường đi ngắn nhất đến *Player* (với điều kiện tồn tại một lối đi đến *Player* mà không có *Brick* hay *Wall*), khi *Player* nằm ngoài bán kính đó thì đối tượng này sẽ di chuyển ngẫu nhiên.
3. *Doll* Đây là đối tượng địch giống di chuyển ngẫu nhiên giống như *Balloom* nhưng tốc độ di chuyển được cải thiện so với *Balloom*.
### Power up
Các đối tượng powerup được ẩn dưới các đối tượng *Brick*, sẽ lộ ra khi *Player* dùng *Bomb* để phá hủy các *Brick* đó.
1. *PowerupBomb* Khi người chơi ăn được powerup này, số quả bom có thể đặt sẽ được tăng lên 1 quả.
2. *PowerupFlame* Khi người chơi ăn được powerup này, bán kính phá hủy của quả bom sẽ tăng lên 1 ô.
### Các đối tượng tĩnh
1. *Wall* là tường, người chơi không thể đi qua đối tượng này.
2. *Brick* là gạch, người chơi không thể đi qua đối tượng này, tuy nhiên có thể phá hủy nó bằng bom.
## Ngôn ngữ lập trình và framework
- Ngôn ngữ: Java.
- Framework: JavaFX, FXGL.
