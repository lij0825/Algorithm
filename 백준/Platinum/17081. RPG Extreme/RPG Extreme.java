import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static String[][] board;
	static Hero hero;
	static List<Monster> monlist = new ArrayList<>();
	static List<Item> boxlist = new ArrayList<>();
	static int N, M, sy, sx;
	static boolean endflag = false;
	static Monster monster;
	static boolean fisrtatk, HUfalg, useticket = false, bossflag = false;
	// L, R, U, D
	static int[] dy = { 0, 0, -1, 1 }, dx = { -1, 1, 0, 0 };
	static Item item;

	// static List
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new String[N][M];
		int monstercnt = 0;
		int boxcnt = 0;
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				board[i][j] = input[j];
				if (board[i][j].equals("@")) {
					board[i][j] = ".";
					sy = i;
					sx = j;
					hero = new Hero(i, j, 1, 20, 20, 0, 5, 2, 2, 0, 0);
				}
				if (board[i][j].equals("&") || board[i][j].equals("M")) {
					monstercnt++;
				}
				if (board[i][j].equals("B")) {
					boxcnt++;
				}
			}
		}

		// 이동 명령
		String[] move = br.readLine().split("");
		// 몬스터 입력
		for (int i = 0; i < monstercnt; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			int w = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int hp = Integer.parseInt(st.nextToken());
			int exp = Integer.parseInt(st.nextToken());
			if (board[y - 1][x - 1].equals("M")) {
				bossflag = true;
				monlist.add(new Monster(y, x, w, a, hp, exp, name, "Boss"));
			} else {
				monlist.add(new Monster(y, x, w, a, hp, exp, name, "Nomal"));
			}
		}
		// 상자 입력
		for (int i = 0; i < boxcnt; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			String type = st.nextToken();
			String effect = st.nextToken();
			boxlist.add(new Item(y, x, type, effect));
		}

		int turn = 0;
		for (int i = 0; i < move.length; i++) {
			if (endflag) {
				break;
			}

			int dir = -1;
			int y = hero.y;
			int x = hero.x;
			switch (move[i]) {
			case "L":
				dir = 0;
				break;
			case "R":
				dir = 1;
				break;
			case "U":
				dir = 2;
				break;
			case "D":
				dir = 3;
				break;
			}
			turn++;
			int ny = y + dy[dir];
			int nx = x + dx[dir];

			if (!isIn(ny, nx) || board[ny][nx].equals("#")) {
				if (board[y][x].equals("^")) {
					trap(ny, nx);
				}
				continue;
			}
			if (board[ny][nx].equals("^")) {
				trap(ny, nx);
				continue;
			}
			if (board[ny][nx].equals("&") || board[ny][nx].equals("M")) {
				battle(ny, nx);
				continue;
			}
			if (board[ny][nx].equals("B")) {
				findbox(ny, nx);
				hero.y = ny;
				hero.x = nx;
				continue;
			}
			if (board[ny][nx].equals(".")) {
				hero.y = ny;
				hero.x = nx;
				continue;
			}

		}
		if (hero.hp > 0) {
			board[hero.y][hero.x] = "@";
		}
		for (String[] ss : board) {
			for (String s : ss) {
				sb.append(s);
			}
			sb.append("\n");
		}
		sb.append("Passed Turns : " + turn);
		sb.append("\n");
		sb.append("LV : " + hero.level);
		sb.append("\n");
		sb.append("HP : " + hero.hp + "/" + hero.maxhp);
		sb.append("\n");
		sb.append("ATT : " + hero.atk + "+" + hero.w);
		sb.append("\n");
		sb.append("DEF : " + hero.def + "+" + hero.a);
		sb.append("\n");
		sb.append("EXP : " + hero.exp + "/" + hero.needexp);
		sb.append("\n");
		boolean bosslive = false;
		if (bossflag) {
			for (Monster monster : monlist) {
				if (monster.type.equals("Boss")) { // 보스가 살아있으면
					bosslive = true;
					break;
				}
			}
		}

		if (hero.hp > 0 && bossflag && !bosslive) {
			sb.append("YOU WIN!");
		} else if (hero.hp <= 0) {
			sb.append("YOU HAVE BEEN KILLED BY " + monster.name + "..");
		} else if (turn == move.length) {
			sb.append("Press any key to continue.");
		}

		System.out.println(sb);
	}

	static void battle(int y, int x) {

		hero.y = y;
		hero.x = x;

		for (int i = 0; i < monlist.size(); i++) {
			if (monlist.get(i).y == y + 1 && monlist.get(i).x == x + 1) {
				monster = monlist.get(i);
				break;
			}
		}

		fisrtatk = true;
		HUfalg = false;

		for (int i = 0; i < 4; i++) {
			if (hero.artifact[i] != null && hero.artifact[i].equals("HU")) {
				HUfalg = true;
			}

		}
		if (monster.type.equals("Boss") && HUfalg) {
			hero.hp = hero.maxhp;
		}
		while (hero.hp > 0 && monster.hp > 0) {
			heroatk();
			if (monster.hp <= 0) {
				break;
			}
			monatk();
		}

		if (hero.hp > 0) {
			for (int i = 0; i < 4; i++) {
				if (hero.artifact[i] != null && hero.artifact[i].equals("HR")) {
					hero.hp = Math.min(hero.hp + 3, hero.maxhp);
				}
			}
			monsterdie();
		} else {
			herodie();
		}
	}

	static void levelup() {
		hero.level += 1;
		hero.maxhp += 5;
		hero.atk += 2;
		hero.def += 2;
		hero.hp = hero.maxhp;
		hero.exp = 0;
		hero.needexp = 5 * hero.level;
	}

	static void heroatk() {
		if (fisrtatk) {
			fisrtatk = false;
			boolean doubleflag = false;
			for (int i = 0; i < 4; i++) {
				if (hero.artifact[i] != null && hero.artifact[i].equals("CO")) {
					doubleflag = true;
				}
			}
			if (doubleflag) {
				boolean tripleflag = false;
				for (int i = 0; i < 4; i++) {
					if (hero.artifact[i] != null && hero.artifact[i].equals("DX")) {
						tripleflag = true;
					}
				}
				if (tripleflag) { // max(1, 공격력×3 – 몬스터의 방어력)
					monster.hp -= Math.max(1, (hero.atk + hero.w) * 3 - monster.a);
				} else { // max(1, 공격력×2 – 몬스터의 방어력)
					monster.hp -= Math.max(1, (hero.atk + hero.w) * 2 - monster.a);
				}
			} else {
				monster.hp -= Math.max(1, hero.atk + hero.w - monster.a);
			}
		} else {
			monster.hp -= Math.max(1, hero.atk + hero.w - monster.a);
		}
	}

	static void monatk() {
		if (monster.type.equals("Boss") && HUfalg) {
			HUfalg = false;
		} else {
			hero.hp -= Math.max(1, monster.w - (hero.def + hero.a));
		}
	}

	static void findbox(int y, int x) {
		for (int i = 0; i < boxlist.size(); i++) {
			if (y + 1 == boxlist.get(i).y && x + 1 == boxlist.get(i).x) {
				item = boxlist.get(i);
				break;
			}
		}
		board[y][x] = ".";
		boolean flag = false;
		if (item.type.equals("O")) {
			for (int i = 0; i < 4; i++) {
				if (hero.artifact[i] != null && hero.artifact[i].equals(item.effect)) {
					flag = true;
				}

			}
			for (int i = 0; i < 4; i++) {
				if (!flag && hero.artifact[i] == null) {
					hero.artifact[i] = item.effect;

					break;
				}
			}
		} else if (item.type.equals("W")) {
			hero.w = Integer.parseInt(item.effect);
		} else if (item.type.equals("A")) {
			hero.a = Integer.parseInt(item.effect);
		}

	}

	static void trap(int y, int x) {
		if (isIn(y, x) && !board[y][x].equals("#")) {
			hero.y = y;
			hero.x = x;
		}

		boolean flag = false;

		for (int i = 0; i < 4; i++) {
			if (hero.artifact[i] != null && hero.artifact[i].equals("DX")) {
				flag = true;
			}
		}

		if (flag) {
			hero.hp -= 1;
		} else {
			hero.hp -= 5;
		}

		if (hero.hp <= 0) {
			if (monster == null) {
				monster = new Monster(0, 0, 0, 0, 0, 0, "SPIKE TRAP", "Nomal");
			} else {
				monster.name = "SPIKE TRAP";
			}
			herodie();
		}

	}

	static void monsterdie() {
		int y = -1, x = -1;

		boolean flag = false;
		for (int i = 0; i < monlist.size(); i++) {
			if (monster.name.equals(monlist.get(i).name)) {
				for (int j = 0; j < 4; j++) {
					if (hero.artifact[j] != null && hero.artifact[j].equals("EX")) {
						flag = true;
					}
				}
				if (flag) {
					hero.exp += (monster.exp + ((monster.exp * 2) / 10));
					if (hero.exp >= hero.needexp) {
						levelup();
					}
				} else {
					hero.exp += monster.exp;
					if (hero.exp >= hero.needexp) {
						levelup();
					}
				}
				if (monster.type.equals("Boss")) {
					endflag = true;
				}
				y = monlist.get(i).y - 1;
				x = monlist.get(i).x - 1;
				monlist.remove(i);
				break;
			}
		}
		board[y][x] = ".";
	}

	static void herodie() {
		hero.hp = 0;

		boolean flag = false;

		for (int i = 0; i < 4; i++) {
			if (hero.artifact[i] != null && hero.artifact[i].equals("RE")) {
				flag = true;
				hero.artifact[i] = null;
			}
		}
		if (flag) {
			useticket = true;
			hero.hp = hero.maxhp;
			hero.y = sy;
			hero.x = sx;
		} else {
			endflag = true;
		}

	}

	static boolean isIn(int y, int x) {
		return !(y < 0 || x < 0 || y >= N || x >= M);
	}

	static class Item {
		int y, x;
		String type, effect;

		public Item(int y, int x, String type, String effect) {
			super();
			this.y = y;
			this.x = x;
			this.type = type;
			this.effect = effect;
		}

		@Override
		public String toString() {
			return "item [y=" + y + ", x=" + x + ", type=" + type + ", effect=" + effect + "]";
		}

	}

	static class Monster {
		// 좌표 , 이름 , 공 , 방 , 체력 , 경험치
		int y, x, w, a, hp, exp;
		String name, type;

		public Monster(int y, int x, int w, int a, int hp, int exp, String name, String type) {
			super();
			this.y = y;
			this.x = x;
			this.w = w;
			this.a = a;
			this.hp = hp;
			this.exp = exp;
			this.name = name;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Monster [y=" + y + ", x=" + x + ", w=" + w + ", a=" + a + ", hp=" + hp + ", exp=" + exp + ", name="
					+ name + ", type=" + type + "]";
		}

	}

	static class Hero {
		// 좌표 , 레벨 , 현재 체력, 최대 체력, 현재경험치, 필요경험치, 공격력 , 방어력, 무기 공격력, 갑옷 방어력
		int y, x, level, hp, maxhp, exp, needexp, atk, def, w, a;
		String[] artifact = new String[4]; // 악세

		public Hero(int y, int x, int level, int hp, int maxhp, int exp, int needexp, int atk, int def, int w, int a) {
			super();
			this.y = y;
			this.x = x;
			this.level = level;
			this.hp = hp;
			this.maxhp = maxhp;
			this.exp = exp;
			this.needexp = needexp;
			this.atk = atk;
			this.def = def;
			this.w = w;
			this.a = a;
		}

		@Override
		public String toString() {
			return "Hero [y=" + y + ", x=" + x + ", level=" + level + ", hp=" + hp + ", maxhp=" + maxhp + ", exp=" + exp
					+ ", needexp=" + needexp + ", atk=" + atk + ", def=" + def + ", w=" + w + ", a=" + a + ", artifact="
					+ Arrays.toString(artifact) + "]";
		}

	}
}