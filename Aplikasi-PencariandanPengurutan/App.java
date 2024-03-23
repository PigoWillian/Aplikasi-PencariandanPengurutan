import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Data {
    private int nilai;
    private String deskripsi;

    public Data(int nilai, String deskripsi) {
        this.nilai = nilai;
        this.deskripsi = deskripsi;
    }

    public int getNilai() {
        return nilai;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}

class DataList {
    private List<Data> dataList;

    public DataList() {
        dataList = new ArrayList<>();
    }

    public void tambahData(Data data) {
        dataList.add(data);
    }

    public Data cariDataLinear(int nilai) {
        for (Data data : dataList) {
            if (data.getNilai() == nilai) {
                return data;
            }
        }
        return null;
    }

    public Data cariDataBiner(int nilai) {
        Collections.sort(dataList, Comparator.comparingInt(Data::getNilai));
        int left = 0;
        int right = dataList.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Data midData = dataList.get(mid);
            if (midData.getNilai() == nilai) {
                return midData;
            } else if (midData.getNilai() < nilai) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public void urutkanDataBubbleSort() {
        for (int i = 0; i < dataList.size() - 1; i++) {
            for (int j = 0; j < dataList.size() - i - 1; j++) {
                if (dataList.get(j).getNilai() > dataList.get(j + 1).getNilai()) {
                    Collections.swap(dataList, j, j + 1);
                }
            }
        }
    }

    public void tampilkanData() {
        for (Data data : dataList) {
            System.out.println("Nilai: " + data.getNilai() + ", Deskripsi: " + data.getDeskripsi());
        }
    }
}

public class App {
    public static void main(String[] args) {
        DataList dataList = new DataList();
        Scanner scanner = new Scanner(System.in);

        // Contoh penggunaan:
        dataList.tambahData(new Data(90, "Data A"));
        dataList.tambahData(new Data(80, "Data B"));
        dataList.tambahData(new Data(70, "Data C"));

        System.out.println("Data yang telah dimasukkan:");
        dataList.tampilkanData();

        System.out.print("Masukkan nilai yang ingin dicari: ");
        int nilaiCari = scanner.nextInt();

        Data hasilPencarianLinear = dataList.cariDataLinear(nilaiCari);
        if (hasilPencarianLinear != null) {
            System.out.println("Hasil pencarian linear: " + hasilPencarianLinear.getDeskripsi());
        } else {
            System.out.println("Data tidak ditemukan (pencarian linear).");
        }

        Data hasilPencarianBiner = dataList.cariDataBiner(nilaiCari);
        if (hasilPencarianBiner != null) {
            System.out.println("Hasil pencarian biner: " + hasilPencarianBiner.getDeskripsi());
        } else {
            System.out.println("Data tidak ditemukan (pencarian biner).");
        }

        dataList.urutkanDataBubbleSort();
        System.out.println("Data setelah diurutkan:");
        dataList.tampilkanData();
    }
}
