CHAPTERS=begin.md ch1.md ch2.md ch3.md ch4.md ch5.md ch6.md ch7.md

main.md: ${CHAPTERS}
	cat ${CHAPTERS} > temp.md
	./cleanutf.py temp.md > main.md
main.html: main.md
	mmd2XHTML.pl main.md
main.tex: main.md
	mmd2LaTeX.pl main.md
main.pdf: main.md
	mmd2PDF.pl main.md
main2.pdf: main.md
	mmd2PDFXeLaTeX.pl main.md

clean:
	rm -f temp.md
	rm -f main.*
	rm -f main2.*

