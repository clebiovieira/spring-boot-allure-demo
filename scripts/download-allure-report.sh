#!/bin/bash

# Script para baixar e visualizar relatórios Allure do GitHub Actions
# Uso: ./download-allure-report.sh [OWNER] [REPO] [RUN_ID]

set -e

# Configurações padrão
OWNER=${1:-"seu-usuario"}
REPO=${2:-"spring-boot-allure-demo"}
RUN_ID=${3}

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}🚀 Script para baixar relatórios Allure do GitHub Actions${NC}"
echo "=================================================="

# Verificar se gh CLI está instalado
if ! command -v gh &> /dev/null; then
    echo -e "${RED}❌ GitHub CLI (gh) não está instalado${NC}"
    echo -e "${YELLOW}💡 Instale com: brew install gh${NC}"
    exit 1
fi

# Verificar se está autenticado
if ! gh auth status &> /dev/null; then
    echo -e "${RED}❌ Não está autenticado no GitHub CLI${NC}"
    echo -e "${YELLOW}💡 Execute: gh auth login${NC}"
    exit 1
fi

# Se RUN_ID não foi fornecido, listar os últimos runs
if [ -z "$RUN_ID" ]; then
    echo -e "${YELLOW}📋 Listando os últimos workflow runs:${NC}"
    gh run list --repo "$OWNER/$REPO" --limit 10
    echo ""
    echo -e "${BLUE}💡 Execute novamente com: $0 $OWNER $REPO [RUN_ID]${NC}"
    exit 0
fi

echo -e "${BLUE}📥 Baixando artefatos do run $RUN_ID...${NC}"

# Criar diretório para downloads
DOWNLOAD_DIR="allure-downloads/run-$RUN_ID"
mkdir -p "$DOWNLOAD_DIR"

# Baixar artefatos
echo -e "${YELLOW}⬇️  Baixando allure-report...${NC}"
gh run download "$RUN_ID" --repo "$OWNER/$REPO" --name "allure-report" --dir "$DOWNLOAD_DIR"

echo -e "${GREEN}✅ Download concluído!${NC}"
echo -e "${BLUE}📁 Arquivos salvos em: $DOWNLOAD_DIR${NC}"

# Verificar se Python está disponível para servir o relatório
if command -v python3 &> /dev/null; then
    echo ""
    echo -e "${YELLOW}🌐 Iniciando servidor HTTP para visualizar o relatório...${NC}"
    echo -e "${GREEN}🔗 Acesse: http://localhost:8080${NC}"
    echo -e "${YELLOW}⏹️  Pressione Ctrl+C para parar o servidor${NC}"
    echo ""
    
    cd "$DOWNLOAD_DIR"
    python3 -m http.server 8080
else
    echo -e "${YELLOW}💡 Para visualizar o relatório, abra o arquivo:${NC}"
    echo -e "${GREEN}   $DOWNLOAD_DIR/index.html${NC}"
fi