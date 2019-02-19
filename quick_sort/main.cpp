#include <iostream>
#include <stdlib.h>
#include <ctime>

using std::cout;
using std::endl;

//kaire - kairys intervalo indeksas
//desine - dešinys intervalo indeksas
void quickSort(int mas[], int kaire, int desine) {
	int vidur = mas[(kaire + desine) / 2]; //vidurinis intervalo elementas
	int k = kaire, d = desine;
	while (k <= d) {
		while (mas[k] < vidur) k++;
		while (mas[d] > vidur) d--;
		if (k <= d) {
			std::swap(mas[k], mas[d]);
			k++; d--;
		}
	}
	if (kaire < d) quickSort(mas, kaire, d);
	if (k < desine) quickSort(mas, k, desine);
}

int main() {
	srand(time(0));
	int *mas = nullptr;
	int masDydis = 0;
	int maxElemDydis = 99;
	int testuSkaicius = 10;
	for (int i = 0; i < testuSkaicius; ++i) {
		//masyvas generuojamas nuo 2 iki 30 elementų
		masDydis = rand() % 29 + 2;
		mas = new int[masDydis];
		cout << "Pradinis masyvas:\n";
		for (int j = 0; j < masDydis; ++j) {
			mas[j] = rand() % maxElemDydis + 1;
			cout << mas[j] << " ";
		} cout << endl;
		quickSort(mas, 0, masDydis - 1);
		cout << "Isrikiuotas masyvas:\n";
		for (int j = 0; j < masDydis; ++j) {
			cout << mas[j] << " ";
		} cout << endl << endl;
		delete[] mas;
	}
}