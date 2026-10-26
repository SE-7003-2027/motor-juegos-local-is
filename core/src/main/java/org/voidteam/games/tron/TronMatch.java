package org.voidteam.games.tron;

/** Reglas para Tron, independientes de la ventana, el teclado y los graficos. */
final class TronMatch {
    // Direcciones posibles de movimiento de los jugadores.
    enum Direction {
        UP(0, 1), DOWN(0, -1), LEFT(-1, 0), RIGHT(1, 0);

        final int dx;
        final int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        boolean isOpposite(Direction other) {
            return dx == -other.dx && dy == -other.dy;
        }
    }

    // Resultado del juego, que indica si sigue en curso o si un jugador ha ganado.
    enum Result {
        RUNNING, PLAYER_ONE_WINS, PLAYER_TWO_WINS, DRAW
    }

    // Representa a un jugador en el juego. Mantiene su posicion, direccion y si tiene un giro pendiente.
    private static final class Player {
        int x;
        int y;
        Direction direction;
        Direction pendingDirection;
        boolean turnQueued;

        Player(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.pendingDirection = direction;
        }
    }

    private final int columns;
    private final int rows;
    private int[][] board;
    private Player playerOne;
    private Player playerTwo;
    private Result result;

    /**
     * Crea un nuevo juego de Tron con el tamaño especificado.
     * @param columns Numero de columnas del tablero.
     * @param rows Numero de filas del tablero.
     * @throws IllegalArgumentException si el tablero es demasiado pequeño.
     */
    TronMatch(int columns, int rows) {
        if (columns < 8 || rows < 4) {
            throw new IllegalArgumentException(
                "El tablero debe tener al menos 8 x 4 celdas"
            );
        }
        this.columns = columns;
        this.rows = rows;
        reset();
    }

    /** Reinicia el juego a su estado inicial. */
    void reset() {
        board = new int[columns][rows];
        playerOne = new Player(columns / 4, rows / 2, Direction.RIGHT);
        playerTwo = new Player(
            columns - 1 - columns / 4, rows / 2, Direction.LEFT
        );
        board[playerOne.x][playerOne.y] = 1;
        board[playerTwo.x][playerTwo.y] = 2;
        result = Result.RUNNING;
    }

    /** Solicita un cambio de direccion para el jugador especificado. */
    void queueDirection(int playerId, Direction requested) {
        if (isOver()) return;

        Player player = player(playerId);
        if (player.turnQueued || requested == player.direction
                || requested.isOpposite(player.direction)) {
            return;
        }

        player.pendingDirection = requested;
        player.turnQueued = true;
    }

    /** Avanza el juego un paso, moviendo a los jugadores y evaluando colisiones. */
    void step() {
        if (isOver()) return;

        playerOne.direction = playerOne.pendingDirection;
        playerTwo.direction = playerTwo.pendingDirection;
        playerOne.turnQueued = false;
        playerTwo.turnQueued = false;

        int nextOneX = playerOne.x + playerOne.direction.dx;
        int nextOneY = playerOne.y + playerOne.direction.dy;
        int nextTwoX = playerTwo.x + playerTwo.direction.dx;
        int nextTwoY = playerTwo.y + playerTwo.direction.dy;

        // Ambos movimientos se evalúan contra el mismo estado del tablero.
        boolean oneCrashes = isBlocked(nextOneX, nextOneY);
        boolean twoCrashes = isBlocked(nextTwoX, nextTwoY);

        if (nextOneX == nextTwoX && nextOneY == nextTwoY) {
            oneCrashes = true;
            twoCrashes = true;
        }

        if (oneCrashes || twoCrashes) {
            if (oneCrashes && twoCrashes) result = Result.DRAW;
            else if (oneCrashes) result = Result.PLAYER_TWO_WINS;
            else result = Result.PLAYER_ONE_WINS;
            return;
        }

        playerOne.x = nextOneX;
        playerOne.y = nextOneY;
        playerTwo.x = nextTwoX;
        playerTwo.y = nextTwoY;
        board[nextOneX][nextOneY] = 1;
        board[nextTwoX][nextTwoY] = 2;
    }

    private boolean isBlocked(int x, int y) {
        return x < 0 || x >= columns
            || y < 0 || y >= rows
            || board[x][y] != 0;
    }

    private Player player(int playerId) {
        return switch (playerId) {
            case 1 -> playerOne;
            case 2 -> playerTwo;
            default -> throw new IllegalArgumentException(
                "Jugador no válido: " + playerId
            );
        };
    }

    int playerX(int playerId) {
        return player(playerId).x;
    }

    int playerY(int playerId) {
        return player(playerId).y;
    }

    int cellOwner(int x, int y) {
        return board[x][y];
    }

    /** Devuelve el resultado del juego. */
    Result result() {
        return result;
    }

    /** Devuelve true si el juego ha terminado. */
    boolean isOver() {
        return result != Result.RUNNING;
    }
}