set outputfolder=%userprofile%\Desktop
echo "Your output foler is: %outputfolder%"
set timestamp=%DATE:/=-%_%TIME::=-%
set timestamp=%timestamp: =%
echo %timestamp%
set outputfolder=%outputfolder%\%timestamp%
echo "Create folder:" %outputfolder%
mkdir %outputfolder%
set env=uat1
set env2=uat4
set env3=uat6
set env4=prelive1
call mvn -DstoreCode=vuse_fr_fr_fr -Denv=eu-%env% -P api-regression clean install  --log-file %outputfolder%\log_vuse_fr_fr_fr.txt
call mvn -DstoreCode=vuse_uk_en_gb -Denv=eu-%env% -P api-regression clean install --log-file %outputfolder%\log_vuse_uk_en_gb.txt
call mvn -DstoreCode=vuse_de_de_de -Denv=eu-%env% -P api-regression clean install --log-file %outputfolder%\log_vuse_de_de_de.txt
call mvn -DstoreCode=vuse_za_en_za -Denv=eu-%env% -P api-regression clean install --log-file %outputfolder%\log_vuse_za_en_za.txt
call mvn -DstoreCode=vuse_co_es_es -Denv=am-%env% -P api-regression clean install --log-file %outputfolder%\log_vuse_co_es_es.txt
call mvn -DstoreCode=vuse_it_it_it -Denv=eu-%env% -P api-regression clean install  --log-file %outputfolder%\log_vuse_it_it_it.txt
call mvn -DstoreCode=glo_kz_ru_kz -Denv=eu-%env% -P api-regression clean install  --log-file %outputfolder%\log_glo_kz_ru_kz.txt
call mvn -DstoreCode=glo_it_it_it -Denv=eu-%env4% -P api-regression clean install --log-file %outputfolder%\log_glo_it_it_it.txt
call mvn -DstoreCode=glo_pl_pl_pl -Denv=eu-%env% -P api-regression-gloPL clean install --log-file %outputfolder%\log_glo_pl_pl_pl.txt
call mvn -DstoreCode=glo_de_de_de -Denv=eu-%env% -P api-regression-gloDE clean install --log-file %outputfolder%\log_glo_de_de_de.txt
call mvn -DstoreCode=lyft_se_sv_se -Denv=eu-%env% -P api-regression-lyftSE clean install  --log-file %outputfolder%\log_lyft_se_sv_se.txt
call mvn -DstoreCode=vuse_mx_es_es -Denv=am-%env% -P api-regression clean install --log-file %outputfolder%\log_vuse_mx_es_es.txt
call mvn -DstoreCode=velo_eu_pl_pl -Denv=eu-%env3% -P api-regression clean install --log-file %outputfolder%\log_velo_eu_pl_pl.txt
call mvn -DstoreCode=velo_eu_be_en -Denv=eu-%env2% -P api-regression-veloBE clean install --log-file %outputfolder%\log_velo_eu_be_en.txt