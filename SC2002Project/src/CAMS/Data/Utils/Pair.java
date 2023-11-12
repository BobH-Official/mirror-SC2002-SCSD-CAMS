package CAMS.Data;

class Pair<K, V> {
  final K first;
  final V second;


  Pair(K first, V second) {
    this.first = first;
    this.second = second;
  }

  K getFirst() {
    return this.first;
  }

  V getSecond() {
    return this.second;
  }
}
