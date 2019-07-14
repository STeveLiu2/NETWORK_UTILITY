import sys
import os
import whois
import socket
import multiprocessing
import subprocess
import speedtest
import threading
import telnetlib
file_loca="C:/Users/Steve Liu/Desktop/New Folder/log.txt"
ecd = "UTF-8"
def ping():
	ip = sys.argv[2]
	tme = int(sys.argv[3])
	if tme!=0:
		pinginfo=subprocess.Popen("ping -n {0} {1}".format(tme,ip),shell=True, stdin = subprocess.PIPE, stdout=subprocess.PIPE, encoding=ecd)
		hd = open("{0}".format(file_loca),'a')
		info = 'line'
		while info:
			info = pinginfo.stdout.readline()
			hd.write(info)
			hd.write('\n')
			if not info:
				break
		hd.close()
	elif tme==0:
		pinginfo=subprocess.Popen("ping -t {0}".format(ip),shell=True, stdin = subprocess.PIPE, stdout=subprocess.PIPE,encoding=ecd)
		hd = open("{0}".format(file_loca),'a')
		info='line'
		while info:
			info = pinginfo.stdout.readline()
			hd.write(info)
			hd.write('\n')
			if not info:
				break
		hd.close()
	sys.exit()
def lookup():
	ip = sys.argv[2]
	lkupinfo = os.popen("nslookup {0}".format(ip))
	info = lkupinfo.read()
	fl = open("{0}".format(file_loca),"w")
	fl.write(info)
	fl.close()
	sys.exit()
def traceroute():
	ip = str(sys.argv[2])
	rt = subprocess.Popen("tracert {0}".format(ip),shell=True, stdin=subprocess.PIPE,stdout=subprocess.PIPE,encoding=ecd)     
	hd = open("{0}".format(file_loca), "a")
	ans="line"
	while ans:
		ans = rt.stdout.readline()
		hd.write(ans)
		if not ans:
			break
	hd.close()
	sys.exit()
def wis():
	ip = str(sys.argv[2])
	info = whois.whois(ip)
	hd = open("{0}".format(file_loca),"a")
	domain = info["domain_name"]
	hd.write("Domain: ")
	hd.write(str(domain))
	hd.write('\n')
	crt = info["creation_date"]
	crt = str(crt[0]).split(', ')
	hd.write("Creation date: ")
	hd.write(crt[0])
	hd.write('\n')
	ext = info["expiration_date"]
	ext = str(ext[0]).split(', ')
	hd.write("Expiration date: ")
	hd.write(ext[0])
	hd.write('\n')
	og = info["org"]
	hd.write("Organization: ")
	hd.write(og)
	hd.write('\n')
	st = info["state"]
	hd.write("State: ")
	hd.write(st)
	hd.write('\n')
	ct = info["country"]
	hd.write("Country: ")
	hd.write(ct)
	hd.write('\n')
	hd.close()
	sys.exit()
def port_scan(ip,port):
	open_port = list()
	server = telnetlib.Telnet()
	try:
		server.open(ip,port,10)
		hd = open("{0}".format(file_loca),"a")
		hd.write('{0}: port {1} is open'.format(ip,port))
		server.close
		hd.close()
	except Exception:
		server.close
def portscan():
	ip = sys.argv[2]
	st = int(sys.argv[3])
	ed = int(sys.argv[4])
	for port in range(st,ed+1):
		t = threading.Thread(target=port_scan,args=(ip,port))
		t.start()
		t.join()
	sys.exit()
def netspeed():
	servers=[]
	threads=None
	s=speedtest.Speedtest()
	s.get_servers(servers)
	s.get_best_server()
	s.download(threads=threads)
	s.upload(threads=threads)
	s.results.share()
	results_dict=s.results.dict()
	fl = open("{0}".format(file_loca),"a")
	dld = str(round((results_dict['download']/1000/1024),3)) + ' ' + 'Mbps'
	fl.write("Download speed: ")
	fl.write(dld)
	fl.write('\n')
	upl = str(round((results_dict['upload']/1000/1024),3))+ ' ' + 'Mbps'
	fl.write("Upload speed: ")
	fl.write(upl)
	fl.write('\n')
	png = str(results_dict['ping']) + ' ' + 'ms'
	fl.write('ping: ')
	fl.write(png)
	fl.write('\n')
	sps = results_dict['server']['sponsor']
	fl.write('Sponsor: ')
	fl.write(sps)
	fl.write('\n')
	sp = results_dict['client']['isp']
	fl.write("Isp: ")
	fl.write(sp)
	fl.write('\n')
	fl.close()
	sys.exit()
def main(function):
	if function=="ping":
		ping()
	if function=="lookup":
		lookup()
	if function=="traceroute":
		traceroute()
	if function=="whois":
		wis()
	if function=="portscan":
		portscan()
	if function=="speedtest":
		netspeed()
if __name__=="__main__":
	ipt = sys.argv[1]
	main(ipt)