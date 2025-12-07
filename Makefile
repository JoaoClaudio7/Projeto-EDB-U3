# --- Variáveis de Configuração ---
JAVAC = javac
JAVA = java
SRC_DIR = src
BIN_DIR = bin

#SOURCES = $(shell find $(SRC_DIR) -name "*.java")
SOURCES = $(wildcard $(SRC_DIR)/*.java)

all: compile

compile:
	@echo "==> Compilando o projeto..."
	@if not exist $(BIN_DIR) mkdir $(BIN_DIR)
	$(JAVAC) -encoding UTF-8 -d $(BIN_DIR) -sourcepath $(SRC_DIR) $(SOURCES)
	@echo "==> Compilação concluída com sucesso."

run: compile
	@echo "==> Executando o Sistema..."
	@echo "------------------------------------------------"
	@$(JAVA) -cp $(BIN_DIR) Main

clean:
	@echo "==> Limpando arquivos compilados..."
	@rm -rf $(BIN_DIR)
	@echo "==> Limpeza concluída."
