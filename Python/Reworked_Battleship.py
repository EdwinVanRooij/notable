# Import modules
from random import randint
import os
import msvcrt as m

# Wait for any keypress
def wait():
    m.getch()

# Fill the board, 5 O's for each array
def filled_board(columns, rows):
    board = []
    for x in range(rows):
        board.append(["O"] * columns)
    return board

# Define a function to clear the screen
def clear_screen():
    os.system(['clear','cls'][os.name == 'nt'])

# Print all the game messages
def print_messages(messages):
    for message in messages:
        print(message)

# Get a random row from the board
def random_row(board):
    return randint(0, len(board) - 1)

# Get a random column from the board
def random_col(board):
    return randint(0, len(board[0]) - 1)

# Print the board, put a space between & center it using spaces left & right
def print_board(board, turns):
    clear_screen()
    print()
    print("Let's play Battleship!".center(50, ' '))
    print()
    
    print(" ".join(" X").center(50, ' '))
    print(("Y "+(" ".join(board[0]))).center(50, ' '))
    for row in board[1:]:
        print((" "+(" ".join(row))).center(50, ' '))
    
    print()
    print("Turns left:", turns)

# Print all the shit
def print_board_w_layout(board, turns_left, messages):
    print_board(board, turns_left)
    print()
    print_messages(messages)
    print()

# Create the board
board = filled_board(int(input("Amount of columns: ")), int(input("Amount of rows: ")))

# Initialize the battleship location
ship_row = random_row(board)
ship_col = random_col(board)

# Let's start the game!
turns_left = int(input("Amount of turns: "))
messages = []

for turn in range(int(turns_left)):
    print_board_w_layout(board, turns_left, messages)

    guess_col = int(input("Guess X: ")) - 1
    guess_row = int(input("Guess Y: ")) - 1
    
    # Stay in the board
    if (guess_row < 0 or guess_row > len(board) - 1 or guess_col < 0 or guess_col > len(board[0]) - 1):
        messages.append("Please stay within the board!")
        continue
    # Don't guess the same twice
    if (board[guess_row][guess_col] == "X"):
        messages.append("You already guessed that one!")
        continue
    # Missed
    if guess_row != ship_row or guess_col != ship_col:
        messages.append("Miss!")
        board[guess_row][guess_col] = "X"
        
    # One less turn counts for all of these above
    turns_left -= 1

    if turns_left == 0:
        messages.append("Game over")
        print_board_w_layout(board, turns_left, messages)
        wait()
        break

    if guess_row == ship_row and guess_col == ship_col:
        messages.append("Congratulations! You win!")
        board[guess_row][guess_col] = "V"
        print_board_w_layout(board, turns_left, messages)
        wait()
        break