Projet Scolaire réalisé par la meilleur équipe de Télécom aka Thunderbirds!

rappel port proxy UJM : http://cache.univ-st-etienne.fr:3128/

set it on git : git config --global http.proxy http://cache.univ-st-etienne.fr:3128/

Warning: certains proxy ont du mal avec des httpS; on change alors avec : git config --global http.proxy http://cache.univ-st-etienne.fr:3128/

pour que ça marche à la maison: git config --global --unset http.sslVerify git config --global --unset http.proxy

Mais à l'école il faut remettre: git config --global http.proxy http://cache.univ-st-etienne.fr:3128/


Application in Java for Comics lover.
