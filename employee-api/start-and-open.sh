#!/bin/bash

URL="http://localhost:8080/swagger-ui.html"

echo "🚀 Starting Docker Compose..."
echo ""

# Start docker-compose in background
docker-compose up --build -d

echo ""
echo "⏳ Menunggu aplikasi siap..."
echo "📝 Monitoring: Started EmployeeApiApplication"
echo ""

# Monitor logs untuk "Started EmployeeApiApplication"
timeout=60
elapsed=0

while [ $elapsed -lt $timeout ]; do
    if docker logs employee-api 2>&1 | grep -q "Started EmployeeApiApplication"; then
        echo ""
        echo "✅ Aplikasi sudah siap!"
        echo "🌐 Membuka browser..."
        sleep 2
        
        # Buka browser berdasarkan OS
        if [[ "$OSTYPE" == "linux-gnu"* ]]; then
            if command -v xdg-open > /dev/null; then
                xdg-open "$URL" &
            elif command -v gnome-open > /dev/null; then
                gnome-open "$URL" &
            fi
        elif [[ "$OSTYPE" == "darwin"* ]]; then
            open "$URL"
        elif [[ "$OSTYPE" == "msys" || "$OSTYPE" == "cygwin" ]]; then
            start "$URL"
        fi
        
        echo "✨ Browser terbuka di $URL"
        echo ""
        echo "📋 Menampilkan logs (Ctrl+C untuk stop)..."
        echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
        docker-compose logs -f
        exit 0
    fi
    
    printf "."
    sleep 2
    elapsed=$((elapsed + 2))
done

echo ""
echo "⚠️ Timeout - Aplikasi mungkin belum siap"
echo "🔗 Silakan buka manual: $URL"
echo ""
docker-compose logs -fchmod +x start-and-open.sh