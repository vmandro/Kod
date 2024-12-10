import asyncio

async def coroutine1():
    task = asyncio.create_task(coroutine2())
    await task
    print(1)

async def coroutine2():
    print(2)
    await asyncio.sleep(3)
   
asyncio.run(coroutine1())
print("finito")
