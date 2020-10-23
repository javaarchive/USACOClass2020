#include<iostream>
#include<set>
#include<map>
#include <limits>
#include <utility>
using namespace std;

// Meant to pass by ref
void swap (int arr[], int a, int b){
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
}
void smallest(int arr[]){
    int minval = INT32_MAX;
    for(int i = 0; i < sizeof(arr)/sizeof(arr[0]); i ++){
        minval = min(arr[i], minval);
    }
}
void largest(int arr[]){
    int maxval = INT32_MIN;
    for(int i = 0; i < sizeof(arr)/sizeof(arr[0]); i ++){
        maxval = max(arr[i], maxval);
    }
}